package com.cnpc.jpro.controller;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.cnpc.framework.utils.StrUtil;
import com.cnpc.jpro.entity.WaterHour;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.cnpc.framework.base.service.BaseService;
import com.cnpc.framework.annotation.RefreshCSRFToken;
import com.cnpc.framework.annotation.VerifyCSRFToken;
import com.cnpc.framework.base.pojo.Result;
import com.cnpc.jpro.entity.StationInfo;

/**
* 站点信息管理控制器
* @author jrn
* 2019-01-10 10:01:21由代码生成器自动生成
*/
@Controller
@RequestMapping("/stationinfo")
public class StationInfoController {

    @Resource
    private BaseService baseService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
        return "jpro/stationinfo_list";
    }

    @RequestMapping(value="/list_exp",method = RequestMethod.GET)
    public String listExp(){
        return "jpro/stationinfo_list_exp";
    }

    @RequestMapping("/getlistdata")
    @ResponseBody
    public Map<String,Object> getlistdata(
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "offset", required = false)Integer offset) {
        Map<String, Object> map = new HashMap<>();
        String sql = "select * from jp_station_info";
        List<StationInfo> list = baseService.findBySql(sql, StationInfo.class);
        map.put("total", 100);//假设共有100条数据
        map.put("rows", list);
        return map;
    }

    @RequestMapping("/get/station/all")
    @ResponseBody
    public List<StationInfo> getAllEnt() {
        String sql = "select * from jp_station_info";
        List<StationInfo> list = baseService.findBySql(sql, StationInfo.class);
        return list;
    }


    @RefreshCSRFToken
    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public String edit(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "jpro/stationinfo_edit";
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
    public String detail(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "jpro/stationinfo_detail";
    }

    @RequestMapping(value="/get/{id}",method = RequestMethod.POST)
    @ResponseBody
    public StationInfo get(@PathVariable("id") String id){
        return baseService.get(StationInfo.class, id);
    }

    @VerifyCSRFToken
    @RequestMapping(value="/save")
    @ResponseBody
    public Result save(String obj){
        StationInfo stationinfo= JSON.parseObject(obj,StationInfo.class);
        //stationinfo.setStCode(baseService.get(StCode.class,stationinfo.getStCode().getId()));
        if(StrUtil.isEmpty(stationinfo.getId())){
            baseService.save(stationinfo);
        }
        else{
            stationinfo.setUpdateDateTime(new Date());
            baseService.update(stationinfo);
        }
        return new Result(true);
    }



    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") String id){
        StationInfo stationinfo=this.get(id);
        try{
            baseService.delete(stationinfo);
            return new Result();
        }
        catch(Exception e){
            return new Result(false,"该数据已经被引用，不可删除");
        }
    }



}
