package com.cnpc.jpro.service.impl;

import com.cnpc.framework.base.pojo.PageInfo;
import com.cnpc.framework.base.service.impl.BaseServiceImpl;
import com.cnpc.framework.exception.QueryException;
import com.cnpc.framework.query.entity.Query;
import com.cnpc.framework.query.entity.QueryCondition;
import com.cnpc.framework.query.util.QueryUtil;
import com.cnpc.jpro.entity.WaterHour;
import com.cnpc.jpro.service.MonitorService;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Huang Jianhai on 2019/1/10.
 */
@Service("monitorService")
public class MonitorServiceImpl extends BaseServiceImpl implements MonitorService {
    @Override
    public List<WaterHour> getWaterHourList(QueryCondition condition, PageInfo pageInfo) {
        String mn = null;
        String btime = null;
        String etime = null;
        if (condition != null) {
            mn = condition.getConditionMap().get("mn").toString();
            btime = condition.getConditionMap().get("btime").toString();
            etime = condition.getConditionMap().get("etime").toString();
        }

        List<WaterHour> groupList = null;
        String sql = "select a.mn,DATE_FORMAT(mtime,'%Y-%m-%d %H') mtime,\n" + "max(case factor_code when 'w00000' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w00000,\n" + "max(case factor_code when 'w01001' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w01001,\n" + "max(case factor_code when 'w01018' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w01018,\n" + "max(case factor_code when 'w21003' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21003,\n" + "max(case factor_code when 'w21011' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21011,\n" + "max(case factor_code when 'w21001' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21001,\n" + "max(case factor_code when 'w20116' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20116,\n" + "max(case factor_code when 'w20117' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20117,\n" + "max(case factor_code when 'w20121' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20121,\n" + "max(case factor_code when 'w20122' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20122,\n" + "max(case factor_code when 'w20120' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20120,\n" + "max(case factor_code when 'w20123' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20123,\n" + "max(case factor_code when 'w20115' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20115,\n" + "max(case factor_code when 'w01020' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w01020,\n" + "max(case factor_code when 'w21016' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21016,\n" + "max(case factor_code when 'w21017' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21017\n" + "from jp_monitor_hour a\n" + "where a.mn=?\n" + "and factor_code in('w00000','w01001','w01018','w21003','w21011',\n" + "    'w21001','w20116','w20117','w20121','w20122',\n" + "    'w20120','w20123','w20115','w01020','w21016','w21017')\n" + "and mtime>=DATE_FORMAT(?,'%Y-%m-%d')\n" + "and mtime<=DATE_FORMAT(CONCAT(?,' 23'),'%Y-%m-%d %H')\n" + "group by mn,mtime\n" + "order by mn,factor_code,mtime";
        try{
            //获取Query配置
            Query query = QueryUtil.getQuery(condition);
            //获取所属的类
            Class<?> objClass = QueryUtil.getClassName(query.getClassName());
            // 分页信息
            //pageInfo = QueryUtil.getPageInfo(condition, query);
            //返回数据
            groupList = this.findMapBySql(sql, query.getCountStr(), pageInfo, new Object[]{mn,btime,etime},
                    new Type[]{StringType.INSTANCE,StringType.INSTANCE,StringType.INSTANCE}, objClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (QueryException e) {
            e.printStackTrace();
        }
        return groupList;
    }
}

/*

###count  简化后效果差不多，直接用原sql
select a.mn from jp_monitor_hour a
where a.mn=?
and factor_code in('w00000','w01001','w01018','w21003','w21011',
    'w21001','w20116','w20117','w20121','w20122',
    'w20120','w20123','w20115','w01020','w21016','w21017')
and mtime>=DATE_FORMAT(?,'%Y-%m-%d')
and mtime<=DATE_FORMAT(CONCAT(?,' 23'),'%Y-%m-%d %H')
group by mn,mtime


select a.mn,DATE_FORMAT(mtime,'%Y-%m-%d %H') mtime,
max(case factor_code when 'w00000' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w00000,
max(case factor_code when 'w01001' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w01001,
max(case factor_code when 'w01018' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w01018,
max(case factor_code when 'w21003' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21003,
max(case factor_code when 'w21011' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21011,
max(case factor_code when 'w21001' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21001,
max(case factor_code when 'w20116' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20116,
max(case factor_code when 'w20117' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20117,
max(case factor_code when 'w20121' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20121,
max(case factor_code when 'w20122' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20122,
max(case factor_code when 'w20120' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20120,
max(case factor_code when 'w20123' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20123,
max(case factor_code when 'w20115' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20115,
max(case factor_code when 'w01020' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w01020,
max(case factor_code when 'w21016' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21016,
max(case factor_code when 'w21017' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21017
from jp_monitor_hour a
where a.mn=?
and factor_code in('w00000','w01001','w01018','w21003','w21011',
    'w21001','w20116','w20117','w20121','w20122',
    'w20120','w20123','w20115','w01020','w21016','w21017')
and mtime>=DATE_FORMAT(?,'%Y-%m-%d')
and mtime<=DATE_FORMAT(CONCAT(?,' 23'),'%Y-%m-%d %H')
group by mn,mtime
order by mn,factor_code,mtime
* */