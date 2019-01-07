package com.cnpc.jpro.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.cnpc.framework.base.dao.RedisDao;
import com.cnpc.framework.base.entity.Dict;
import com.cnpc.framework.constant.RedisConstant;
import com.cnpc.framework.utils.StrUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnpc.framework.base.service.BaseService;
import com.cnpc.framework.annotation.RefreshCSRFToken;
import com.cnpc.framework.annotation.VerifyCSRFToken;
import com.cnpc.framework.base.pojo.Result;
import com.cnpc.jpro.entity.StCode;

/**
* 系统编码管理控制器
* @author jrn
* 2019-01-03 15:29:33由代码生成器自动生成
*/
@Controller
@RequestMapping("/stcode")
public class StCodeController {

    @Resource
    private BaseService baseService;

    @Resource
    public RedisDao redisDao;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
        return "jpro/stcode_list";
    }

    @RefreshCSRFToken
    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public String edit(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "jpro/stcode_edit";
    }

    @RequestMapping(value="/detail",method = RequestMethod.GET)
    public String detail(String id,HttpServletRequest request){
        request.setAttribute("id", id);
        return "jpro/stcode_detail";
    }

    @RequestMapping(value="/get/{id}",method = RequestMethod.POST)
    @ResponseBody
    public StCode get(@PathVariable("id") String id){
        return baseService.get(StCode.class, id);
    }

    @VerifyCSRFToken
    @RequestMapping(value="/save")
    @ResponseBody
    public Result save(StCode stcode){
        if(StrUtil.isEmpty(stcode.getId())){
            baseService.save(stcode);
        }
        else{
            stcode.setUpdateDateTime(new Date());
            baseService.update(stcode);
        }
        return new Result(true);
    }



    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") String id){
        StCode stcode=this.get(id);
        try{
            baseService.delete(stcode);
            return new Result();
        }
        catch(Exception e){
            return new Result(false,"该数据已经被引用，不可删除");
        }
    }


    @RequestMapping(value = "/getStByCode", method = RequestMethod.POST)
    @ResponseBody
    public List<StCode> getStByCode(String code) {
        //return baseService.getStsByCode(code);
        String key = RedisConstant.ST_PRE+ code;
        //System.out.println("key:"+key);
        List StCodes = redisDao.get(key, List.class);
        if (StCodes == null) {
            //String hql = "from StCode where enable='1'";
            //Dict dict = this.get(hql);
            StCodes = baseService.find("from StCode where enable='1'");
            redisDao.add(key, StCodes);
            return StCodes;
        } else {
            return StCodes;
        }
    }

}
