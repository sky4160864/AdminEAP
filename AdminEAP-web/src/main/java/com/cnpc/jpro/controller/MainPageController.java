package com.cnpc.jpro.controller;


import com.cnpc.framework.base.pojo.Result2;
import com.cnpc.framework.base.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/main_page")
public class MainPageController {

    @Resource
    private BaseService baseService;


    /**
     * 页面跳转
     *
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
        return "jpro/main_page";
    }

    @RequestMapping(value="/demo01",method = RequestMethod.GET)
    public String demo01(){
        return "jpro/main_page_demo01";
    }

    @RequestMapping(value="/demo02",method = RequestMethod.GET)
    public String demo02(){
        return "jpro/main_page_demo02";
    }

    @RequestMapping(value="/demo03",method = RequestMethod.GET)
    public String demo03(){
        return "jpro/main_page_demo03";
    }

    @RequestMapping(value="/demo04",method = RequestMethod.GET)
    public String demo04(){
        return "jpro/main_page_demo04";
    }

    @RequestMapping(value="/demo05",method = RequestMethod.GET)
    public String demo05(){
        return "jpro/main_page_demo05";
    }

    @RequestMapping(value="/demo06",method = RequestMethod.GET)
    public String demo06(){
        return "jpro/main_page_demo06";
    }


    @RequestMapping(value="/data",method = RequestMethod.GET)
    @ResponseBody
    public Result2 geData(){
        String sql = "SELECT mm.name,nn.*,\n" + "\tcase when nn.mtime>DATE_ADD(now(),INTERVAL -1 HOUR) then '联网' else '断网' end m_status,\n" + "\tcase when nn.mtime>DATE_ADD(now(),INTERVAL -1 HOUR) then '异常' end c_status\n" + "    from jp_station_info mm\n" + "    LEFT JOIN\n" + "    (\n" + "        select a.mn,DATE_FORMAT(a.mtime,'%Y-%m-%d %H:%i') mtime\n" + "        from jp_monitor_minute a \n" + "        inner join jp_factor_relation b on(a.mn=b.mn and a.factor_code=b.factor_code)\n" + "        inner join (\n" + "            select mn,max(mtime) mtime from jp_monitor_minute \n" + "            where mtime>=DATE_FORMAT(NOW(),'%Y-%m-%d')\n" + "                and mtime<NOW()\n" + "            group by mn\n" + "        ) c on(a.mn=c.mn and a.mtime=c.mtime)\n" + "        GROUP BY a.mn,a.mtime \n" + "    ) nn on(mm.mn=nn.mn)";
        List listList = baseService.executeSql4List(sql,null,true);
        Result2 result = new Result2(true);
        result.setData(listList);
        return result;
    }


}
