package com.cnpc.jpro.controller;

import com.alibaba.fastjson.JSON;
import com.cnpc.framework.annotation.RefreshCSRFToken;
import com.cnpc.framework.annotation.VerifyCSRFToken;
import com.cnpc.framework.base.pojo.Result;
import com.cnpc.framework.base.service.BaseService;
import com.cnpc.tool.message.entity.Message;
import com.cnpc.tool.message.pojo.MessageConstant;
import com.cnpc.tool.message.service.MessageService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
     * 水小时数据显示（右侧）
     *
     * @return
     */
    /*@RequestMapping(value="/list",method = RequestMethod.GET)
    public String listData(){
        return "jpro/waterhour_list";
    }*/



}
