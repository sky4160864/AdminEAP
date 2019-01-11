package com.cnpc.jpro.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 历史数据-水小时数据控制器
 *
 */
@Controller
@RequestMapping("/waterhour")
public class WaterHourController {

    //@Resource
    //private BaseService baseService;


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
     * 水小时数据显示（右侧）
     *
     * @return
     */
    /*@RequestMapping(value="/list",method = RequestMethod.GET)
    public String listData(){
        return "jpro/waterhour_list";
    }*/



}
