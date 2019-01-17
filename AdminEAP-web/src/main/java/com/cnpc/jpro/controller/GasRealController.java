package com.cnpc.jpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
* 系统编码管理控制器
* @author jrn
* 2019-01-03 15:29:33由代码生成器自动生成
*/
@Controller
@RequestMapping("/gasreal")
public class GasRealController {

    //@Resource
    //private BaseService baseService;
    //
    //@Resource
    //public RedisDao redisDao;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
        return "jpro/gasreal_list";
    }

}
