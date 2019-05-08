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
import java.util.List;

/**
 * 工况历史数据-分钟数据
 */
@Controller
@RequestMapping("/cond_md")
public class MonitorMinuteController {

    @Resource
    private BaseService baseService;


    /**
     * 页面跳转
     *
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
        return "jpro/cond_md_list";
    }


    @RequestMapping(value="/get/his_data_head",method = RequestMethod.GET)
    @ResponseBody
    public Result2 getHisDataHead(String mn){
        /*定义表头*/
        List<LayuiTableCol> cols = new ArrayList<>();
        cols.add(new LayuiTableCol("mtime","监控时间",true,180));
        String sql1 = "select * from jp_factor_relation where mn=? order by factor_sort";
        List<FactorRelation> list1 = baseService.findMapBySql(sql1, new Object[]{mn}, new Type[]{StringType.INSTANCE}, FactorRelation.class);
        for(FactorRelation fr:list1){
            if(StringUtils.isNullOrEmpty(fr.getFactorUnit())
                    ||"/".equals(fr.getFactorUnit())){
                cols.add(new LayuiTableCol(fr.getFactorCode(),fr.getFactorName(),false,0));
            }else {
                cols.add(new LayuiTableCol(fr.getFactorCode(),fr.getFactorName()+"["+fr.getFactorUnit()+"]",false,0));
            }
        }
        Result2 result = new Result2(true);
        result.setCols(cols);
        return result;
    }

    @RequestMapping(value="/get/his_data",method = RequestMethod.GET)
    @ResponseBody
    public Result2 getHisData(String mn, String btime, String etime, int page, int limit){
        String sql1 = "select * from jp_factor_relation where mn=? order by factor_sort";
        List<FactorRelation> list1 = baseService.findMapBySql(sql1, new Object[]{mn}, new Type[]{StringType.INSTANCE}, FactorRelation.class);
        /*拼接SQL*/
        StringBuilder sql = new StringBuilder("select mn,DATE_FORMAT(mtime,'%Y-%m-%d %H:%i') mtime ");
        for(FactorRelation fr:list1){
            sql.append(",max(case factor_code when '")
                    .append(fr.getFactorCode())
                    .append("' then val_avg else null end ) ")
                    .append(fr.getFactorCode());
        }
        sql.append(" from jp_monitor_minute ")
            .append(" where mn=?")
            .append(" and mtime>=DATE_FORMAT(?,'%Y-%m-%d')")
            .append(" and mtime<DATE_ADD(DATE_FORMAT(?,'%Y-%m-%d'),INTERVAL 1 DAY)")
            .append(" GROUP BY mn,mtime ");

        String qrySqlCount = "select count(1) from ( "+sql.toString()+" ) aa";
        List listCount = baseService.findMapBySql(qrySqlCount,
                new Object[]{mn,btime,etime},
                new Type[]{StringType.INSTANCE,StringType.INSTANCE,StringType.INSTANCE},
                null);

        String qrySql = "select * from ( "+sql.toString()+" ) aa limit ?,?";
        List list = baseService.findMapBySql(qrySql,
                new Object[]{mn,btime,etime,(page-1)*limit,limit},
                new Type[]{StringType.INSTANCE,StringType.INSTANCE,StringType.INSTANCE, IntegerType.INSTANCE, IntegerType.INSTANCE},
                null);

        //拼接json格式：[{mtime:2019-01-01,v01:0},{}]
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            Object[] ob = (Object[])list.get(i);
            if(i>0){
                sb.append(",") ;
            }
            int obIndex = 1; //mn不需要显示，从1开始
            sb.append("{\"mtime\":\"").append(ob[obIndex++]).append("\""); //mtime

            for(FactorRelation fr:list1){
                sb.append(",\"").append(fr.getFactorCode()).append("\":").append(ob[obIndex++])
                    .append(",templet: '#").append(fr.getFactorCode()).append("Tpl'");
            }
            sb.append("}");
        }
        sb.append("]");
        Result2 result = new Result2(true);
        result.setData(sb.toString());
        result.setCount(listCount.size()>0?listCount.get(0):0);
        return result;
    }


    @RequestMapping(value="/get/his_data_check",method = RequestMethod.GET)
    @ResponseBody
    public Result2 getHisDataCheck(String mn, String btime, String etime, int page, int limit){
        String sql1 = "select * from jp_factor_relation where mn=? order by factor_sort";
        List<FactorRelation> list1 = baseService.findMapBySql(sql1, new Object[]{mn}, new Type[]{StringType.INSTANCE}, FactorRelation.class);
        /*拼接SQL*/
        StringBuilder sql = new StringBuilder("select a.mn,DATE_FORMAT(mtime,'%Y-%m-%d %H:%i') mtime ");
        for(FactorRelation fr:list1){
            sql.append(",max(case a.factor_code when '") //CHAR(13)
                    .append(fr.getFactorCode())
                    .append("' then ")
                    .append(" case when (upper_limit is not null and upper_limit>0 and val_avg>upper_limit) or (lower_limit is not null  and val_avg<lower_limit) then ")
                    .append("   concat('<span style=''color:red''>',0+convert(val_avg,char),'</span>') else 0+convert(val_avg,char) end " )
                            .append(" else '' end ) ")
                    .append(fr.getFactorCode());
        }
        sql.append(" from jp_monitor_minute a inner join jp_factor_relation b on(a.mn=b.mn and a.factor_code=b.factor_code)")
                .append(" where a.mn=?")
                .append(" and mtime>=DATE_FORMAT(?,'%Y-%m-%d')")
                .append(" and mtime<DATE_ADD(DATE_FORMAT(?,'%Y-%m-%d'),INTERVAL 1 DAY)")
                .append(" GROUP BY mn,mtime ");

        String qrySqlCount = "select count(1) from ( "+sql.toString()+" ) aa";
        List listCount = baseService.findMapBySql(qrySqlCount,
                new Object[]{mn,btime,etime},
                new Type[]{StringType.INSTANCE,StringType.INSTANCE,StringType.INSTANCE},
                null);

        String qrySql = "select * from ( "+sql.toString()+" ) aa limit ?,?";
        List list = baseService.findMapBySql(qrySql,
                new Object[]{mn,btime,etime,(page-1)*limit,limit},
                new Type[]{StringType.INSTANCE,StringType.INSTANCE,StringType.INSTANCE, IntegerType.INSTANCE, IntegerType.INSTANCE},
                null);

        //拼接json格式：[{mtime:2019-01-01,v01:0},{}]
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            Object[] ob = (Object[])list.get(i);
            if(i>0){
                sb.append(",") ;
            }
            int obIndex = 1; //mn不需要显示，从1开始
            sb.append("{\"mtime\":\"").append(ob[obIndex++]).append("\""); //mtime

            for(FactorRelation fr:list1){
                sb.append(",\"").append(fr.getFactorCode()).append("\":\"").append(ob[obIndex++]).append("\""); //v01
            }
            sb.append("}");
        }
        sb.append("]");
        Result2 result = new Result2(true);
        result.setData(sb.toString());
        result.setCount(listCount.size()>0?listCount.get(0):0);
        return result;
    }
}
