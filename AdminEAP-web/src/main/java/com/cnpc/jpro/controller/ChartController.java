package com.cnpc.jpro.controller;


import com.cnpc.framework.base.service.BaseService;
import com.cnpc.jpro.entity.FactorRelation;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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

    @RequestMapping(value="/get/factors",method = RequestMethod.GET)
    @ResponseBody
    public List<FactorRelation> getStationFactors(String mn){
        String sql1 = "select * from jp_factor_relation where mn=? order by factor_order";
        return  baseService.findMapBySql(sql1, new Object[]{mn}, new Type[]{StringType.INSTANCE}, FactorRelation.class);
    }

    @RequestMapping(value="/get/datas",method = RequestMethod.GET)
    @ResponseBody
    public List<FactorRelation> getStationDatas(String mn,String factors,String btime,String etime){
        String sql1 = "select * from jp_factor_relation where mn=? order by factor_order";
        return  baseService.findMapBySql(sql1, new Object[]{mn}, new Type[]{StringType.INSTANCE}, FactorRelation.class);
    }

}
