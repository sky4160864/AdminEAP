package com.cnpc.jpro.controller;


import com.cnpc.framework.base.pojo.Result2;
import com.cnpc.framework.base.service.BaseService;
import com.cnpc.jpro.entity.FactorRelation;
import com.cnpc.jpro.pojo.LayuiTableCol;
import com.mysql.jdbc.StringUtils;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/monitor_real")
public class MonitorRealController {

    @Resource
    private BaseService baseService;


    /**
     * 页面跳转
     *
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){
        return "jpro/monitor_real_list";
    }


    @RequestMapping(value="/last_data",method = RequestMethod.GET)
    @ResponseBody
    public Result2 geData(){
        String sql = "SELECT mm.name,nn.*,\n" + "\tcase when nn.mtime>DATE_ADD(now(),INTERVAL -1 HOUR) then '联网' else '断网' end m_status,\n" + "\tcase when nn.mtime>DATE_ADD(now(),INTERVAL -1 HOUR) then '异常' end c_status\n" + "    from jp_station_info mm\n" + "    LEFT JOIN\n" + "    (\n" + "        select a.mn,DATE_FORMAT(a.mtime,'%Y-%m-%d %H:%i') mtime\n" + "        from jp_monitor_minute a \n" + "        inner join jp_factor_relation b on(a.mn=b.mn and a.factor_code=b.factor_code)\n" + "        inner join (\n" + "            select mn,max(mtime) mtime from jp_monitor_minute \n" + "            where mtime>=DATE_FORMAT(NOW(),'%Y-%m-%d')\n" + "                and mtime<NOW()\n" + "            group by mn\n" + "        ) c on(a.mn=c.mn and a.mtime=c.mtime)\n" + "        GROUP BY a.mn,a.mtime \n" + "    ) nn on(mm.mn=nn.mn)";
        List listList = baseService.executeSql4List(sql,null,true);
        Result2 result = new Result2(true);
        result.setData(listList);
        return result;
    }

    /*
    SELECT mm.name,nn.*,case when nn.mtime>DATE_ADD(now(),INTERVAL -1 HOUR) then '联网' else '断网' end m_status
    from jp_station_info mm
    LEFT JOIN(
        select a.mn,DATE_FORMAT(a.mtime,'%Y-%m-%d %H:%i') mtime,
            max(CASE a.factor_code WHEN 'v01' THEN CONCAT(factor_name,'[',0+CONVERT(a.val_avg,CHAR),factor_unit,']') END) val01,
            max(CASE a.factor_code WHEN 'v02' THEN CONCAT(factor_name,'[',0+CONVERT(a.val_avg,CHAR),factor_unit,']') END) val02,
            max(CASE a.factor_code WHEN 'v03' THEN CONCAT(factor_name,'[',0+CONVERT(a.val_avg,CHAR),factor_unit,']') END) val03,
            max(CASE a.factor_code WHEN 's01' THEN CONCAT(factor_name,'[',0+CONVERT(a.val_avg,CHAR),factor_unit,']') END) val04,
            max(CASE a.factor_code WHEN 's02' THEN CONCAT(factor_name,'[',0+CONVERT(a.val_avg,CHAR),factor_unit,']') END) val05,
            max(CASE a.factor_code WHEN 'f01' THEN CONCAT(factor_name,'[',0+CONVERT(a.val_avg,CHAR),factor_unit,']') END) val06,
            max(CASE a.factor_code WHEN 'ft01' THEN CONCAT(factor_name,'[',0+CONVERT(a.val_avg,CHAR),factor_unit,']') END) val07
        from jp_monitor_minute a
        inner join jp_factor_relation b on(a.mn=b.mn and a.factor_code=b.factor_code)
        inner join (
            select mn,max(mtime) mtime from jp_monitor_minute
            where mtime>=DATE_FORMAT(NOW(),'%Y-%m-%d')
                and mtime<NOW()
                #and mtime<DATE_ADD(DATE_FORMAT(NOW(),'%Y-%m-%d'),INTERVAL 1 DAY) #要改
            group by mn
        ) c on(a.mn=c.mn and a.mtime=c.mtime)
        GROUP BY a.mn,a.mtime
    ) nn on(mm.mn=nn.mn)


    用这个
    SELECT mm.name,nn.*,
	case when nn.mtime>DATE_ADD(now(),INTERVAL -1 HOUR) then '联网' else '断网' end m_status,
	case when nn.mtime>DATE_ADD(now(),INTERVAL -1 HOUR) then '异常' end c_status
    from jp_station_info mm
    LEFT JOIN
    (
        select a.mn,DATE_FORMAT(a.mtime,'%Y-%m-%d %H:%i') mtime
        from jp_monitor_minute a
        inner join jp_factor_relation b on(a.mn=b.mn and a.factor_code=b.factor_code)
        inner join (
            select mn,max(mtime) mtime from jp_monitor_minute
            where mtime>=DATE_FORMAT(NOW(),'%Y-%m-%d')
                and mtime<NOW()
            group by mn
        ) c on(a.mn=c.mn and a.mtime=c.mtime)
        GROUP BY a.mn,a.mtime
    ) nn on(mm.mn=nn.mn)
    */
}
