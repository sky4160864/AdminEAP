package com.cnpc.jpro.controller;


import com.cnpc.framework.base.pojo.Result;
import com.cnpc.framework.base.service.BaseService;
import com.cnpc.jpro.entity.FactorRelation;
import com.mysql.jdbc.StringUtils;
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
 * Echarts
 */
@Controller
@RequestMapping("/chart")
public class ChartController {

    @Resource
    private BaseService baseService;

    /**站点图*/
    @RequestMapping(value="/station",method = RequestMethod.GET)
    public String list(){
        return "jpro/station_chart";
    }

    /**获取监控因子*/
    @RequestMapping(value="/get/factors",method = RequestMethod.GET)
    @ResponseBody
    public List<FactorRelation> getStationFactors(String mn){
        String sql1 = "select * from jp_factor_relation where mn=? order by factor_sort";
        return  baseService.findMapBySql(sql1, new Object[]{mn}, new Type[]{StringType.INSTANCE}, FactorRelation.class);
    }



    /**获取数据*/
    @RequestMapping(value="/get/chart_data",method = RequestMethod.GET)
    @ResponseBody
    public Result getChartData(String mn,String factors,String btime,String etime){
        /*update jp_monitor_minute set val_avg=round(80+rand()*5,2) where factor_code='v01'
        update jp_monitor_minute set val_avg=round(40+rand()*5,2) where factor_code='v01'
        AND MTIME>=DATE_FORMAT('2019-05-01 12','%Y-%m-%d %H')
        and mtime<DATE_FORMAT('2019-05-01 13','%Y-%m-%d %H')*/
        //http://localhost:8080/chart/get/chart_data?mn=AAAAAAAABBBBBBBBCCCCCCCC000&factors=v01,v02&btime=2019-05-01&etime=2019-05-02
        if(StringUtils.isNullOrEmpty(factors)){
            return new Result(false);
        }
        Map map  = new HashMap<>();

        String sql = "select val_avg from jp_monitor_minute \n" +
                "where mn=? and factor_code=?\n" +
                "\tAND MTIME>=DATE_FORMAT(?,'%Y-%m-%d')\n" +
                "\tand mtime<DATE_ADD(DATE_FORMAT(?,'%Y-%m-%d'),INTERVAL 1 DAY)\n" +
                "\tORDER BY mn,FACTOR_CODE,MTIME";

        String[] fs = factors.split(",");
        for(String factor:fs){
            List _list =  baseService.findMapBySql(sql,
                    new Object[]{mn,factor,btime,etime},
                    new Type[]{StringType.INSTANCE,StringType.INSTANCE,StringType.INSTANCE,StringType.INSTANCE},
                    null);
            map.put(factor,_list);
        }

        String sql2 = sql.replace("val_avg","DISTINCT DATE_FORMAT(mtime,'%d %H:%i')");//(mtime,'%Y-%m-%d %H:%i')
        List _list =  baseService.findMapBySql(sql2,
                new Object[]{mn,fs[0],btime,etime},
                new Type[]{StringType.INSTANCE,StringType.INSTANCE,StringType.INSTANCE,StringType.INSTANCE},
                null);
        map.put("xAxis",_list);

        Result result = new Result(true);
        result.setData(map);
        return result;
    }

}
