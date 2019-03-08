package com.cnpc.jpro.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/formEdit")
public class FormEditController {

    //@Resource
    //private BaseService baseService;
    //
    //@Resource
    //public RedisDao redisDao;

    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public String edit(){
        // System.out.println("1111111111111111111111");
        return "jpro/form_edit";
    }

}
