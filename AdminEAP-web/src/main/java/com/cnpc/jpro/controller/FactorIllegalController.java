package com.cnpc.jpro.controller;


import com.cnpc.framework.base.pojo.Result2;
import com.cnpc.framework.base.service.BaseService;
import com.cnpc.jpro.entity.FactorRelation;
import com.cnpc.jpro.pojo.LayuiTableCol;
import com.mysql.jdbc.StringUtils;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 违规
 */
@Controller
@RequestMapping("/factor_illegal")
public class FactorIllegalController {

    @Resource
    private BaseService baseService;


    /**
     * 页面跳转
     *
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
        return "jpro/factor_illegal";
    }



    @RequestMapping(value="/get_data",method = RequestMethod.GET)
    @ResponseBody
    public Result2 getHisData(String mn, String btime, String etime, int page, int limit){
        String sql = "select c.name,b.rule_name,rule_desc,a.mn,DATE_FORMAT(a.mtime,'%Y-%m-%d %H:%i') mtime\n"
                + "from jp_factor_illegal a\n"
                + "\tINNER JOIN jp_factor_rule b on(a.rule_id=b.id)\n"
                + "\tINNER JOIN jp_station_info c on(c.id=b.station_id)"
                + "\tWHERE 1=1";
        Map<String,Object> params = new HashMap<>();
        if(!StringUtils.isNullOrEmpty(mn)){
            sql = sql +" AND a.mn=:mn";
            params.put("mn",mn);
        }
        if(!StringUtils.isNullOrEmpty(btime)){
            sql = sql +" AND mtime>= DATE_FORMAT(:btime,'%Y-%m-%d %H')";
            params.put("btime",btime);
        }
        if(!StringUtils.isNullOrEmpty(etime)){
            sql = sql +" AND mtime< DATE_ADD(DATE_FORMAT(:etime,'%Y-%m-%d'),INTERVAL 1 DAY)";
            params.put("etime",etime);
        }
        String sqlCount = "select count(*) count from ("+sql+") aa";
        List listCount = baseService.executeSql4List(sqlCount,params);
        String sqlList = "select * from ("+sql+") aa limit :page,:limit";
        // (page-1)*limit,limit
        params.put("page",(page-1)*limit);
        params.put("limit",limit);
        List listList = baseService.executeSql4List(sqlList,params,true);

        Result2 result = new Result2(true);
        result.setCount((listCount!=null&&listCount.size()>0)?listCount.get(0):0);
        result.setData(listList);
        return result;
    }


    @RequestMapping(value="/monitor_data",method = RequestMethod.GET)
    @ResponseBody
    public Result2 getMonitorData(String mn, String mtime){
        if(StringUtils.isNullOrEmpty(mn)||StringUtils.isNullOrEmpty(mtime)){
            return new Result2(false);
        }

        String sql = "select a.factor_name,0+convert(b.val_avg,char) val,a.factor_unit,a.lower_limit,a.upper_limit\n"
                + "from jp_factor_relation a\n"
                + "INNER JOIN jp_monitor_minute b on(a.mn=b.mn and a.factor_code=b.factor_code)\n"
                + "where a.mn=:mn\n"
                + "\tand mtime=DATE_FORMAT(:mtime,'%Y-%m-%d %H:%i')";

        Map<String,Object> params = new HashMap<>();
        params.put("mn",mn);
        params.put("mtime",mtime);
        Result2 result = new Result2(true);
        List listList = baseService.executeSql4List(sql,params,true);
        result.setData(listList);
        return result;
    }


}
