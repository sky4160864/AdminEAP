package com.cnpc.jpro.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.cnpc.framework.utils.StrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
