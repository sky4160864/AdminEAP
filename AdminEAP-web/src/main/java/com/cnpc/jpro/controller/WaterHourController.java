package com.cnpc.jpro.controller;


import com.cnpc.framework.annotation.RefreshCSRFToken;
import com.cnpc.framework.base.pojo.Result;
import com.cnpc.framework.base.service.BaseService;
import com.cnpc.jpro.entity.WaterHour;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 历史数据-水小时数据控制器
 *
 */
@Controller
@RequestMapping("/waterhour")
public class WaterHourController {

    @Resource
    private BaseService baseService;


    /**
     * 水小时数据主页
     *
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
        return "jpro/waterhour_list";
    }

    /**
     * 水小时数据图表
     *
     * @return
     */
    @RequestMapping(value="/chart",method = RequestMethod.GET)
    public String chart(String mn,String btime,String etime,HttpServletRequest request){
        request.setAttribute("mn", mn);
        request.setAttribute("btime", btime);
        request.setAttribute("etime", etime);
        return "jpro/waterhour_chart";
    }

    /**
     * 水小时数据图表
     *
     * @return
     */
    @RequestMapping(value="/chart_data/{mn}/{btime}/{etime}",method = RequestMethod.POST)
    //@RequestMapping(value="/chart_data/{mn}",method = RequestMethod.POST)
    @ResponseBody
    public Result chartData(@PathVariable("mn") String mn,
                            @PathVariable("btime") String btime,
                            @PathVariable("etime") String etime){
        Result result = new Result(true);
        String sql = "select a.mn,DATE_FORMAT(mtime,'%Y-%m-%d %H') mtime,\n" + "max(case factor_code when 'w00000' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w00000,\n" + "max(case factor_code when 'w01001' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w01001,\n" + "max(case factor_code when 'w01018' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w01018,\n" + "max(case factor_code when 'w21003' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21003,\n" + "max(case factor_code when 'w21011' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21011,\n" + "max(case factor_code when 'w21001' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21001,\n" + "max(case factor_code when 'w20116' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20116,\n" + "max(case factor_code when 'w20117' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20117,\n" + "max(case factor_code when 'w20121' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20121,\n" + "max(case factor_code when 'w20122' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20122,\n" + "max(case factor_code when 'w20120' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20120,\n" + "max(case factor_code when 'w20123' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20123,\n" + "max(case factor_code when 'w20115' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20115,\n" + "max(case factor_code when 'w01020' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w01020,\n" + "max(case factor_code when 'w21016' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21016,\n" + "max(case factor_code when 'w21017' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21017\n" + "from jp_monitor_hour a\n" + "where a.mn=?\n" + "and factor_code in('w00000','w01001','w01018','w21003','w21011',\n" + "    'w21001','w20116','w20117','w20121','w20122',\n" + "    'w20120','w20123','w20115','w01020','w21016','w21017')\n" + "and mtime>=DATE_FORMAT(?,'%Y-%m-%d')\n" + "and mtime<=DATE_FORMAT(CONCAT(?,' 23'),'%Y-%m-%d %H')\n" + "group by mn,mtime\n" + "order by mn,factor_code,mtime";
        List<WaterHour> list = baseService.findMapBySql(sql, new Object[]{mn,btime,etime},
                new Type[]{StringType.INSTANCE,StringType.INSTANCE,StringType.INSTANCE}, WaterHour.class);
        result.setData(list);
        return result;
    }




}
