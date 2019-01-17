package com.cnpc.jpro.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.cnpc.framework.base.pojo.PageInfo;
import com.cnpc.framework.base.service.impl.BaseServiceImpl;
import com.cnpc.framework.exception.QueryException;
import com.cnpc.framework.query.entity.Query;
import com.cnpc.framework.query.entity.QueryCondition;
import com.cnpc.framework.query.util.QueryUtil;
import com.cnpc.jpro.entity.GasReal;
import com.cnpc.jpro.entity.WaterHour;
import com.cnpc.jpro.service.MonitorService;
import com.cnpc.util.UtilComm;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(!StringUtils.isEmpty(condition.getSortInfo())){
            sql = "select * from ("+sql+") as aa order by "+condition.getSortInfo();
        }
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

    @Override
    public List<WaterHour> getWaterRealList(QueryCondition condition, PageInfo pageInfo) {
        String sql = "select b.name entName,a.mn,DATE_FORMAT(mtime,'%Y-%m-%d %H:%i') mtime,\n" + "max(case factor_code when 'w00000' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w00000,\n" + "max(case factor_code when 'w01001' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w01001,\n" + "max(case factor_code when 'w01018' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w01018,\n" + "max(case factor_code when 'w21003' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21003,\n" + "max(case factor_code when 'w21011' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21011,\n" + "max(case factor_code when 'w21001' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21001,\n" + "max(case factor_code when 'w20116' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20116,\n" + "max(case factor_code when 'w20117' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20117,\n" + "max(case factor_code when 'w20121' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20121,\n" + "max(case factor_code when 'w20122' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20122,\n" + "max(case factor_code when 'w20120' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20120,\n" + "max(case factor_code when 'w20123' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20123,\n" + "max(case factor_code when 'w20115' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w20115,\n" + "max(case factor_code when 'w01020' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w01020,\n" + "max(case factor_code when 'w21016' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21016,\n" + "max(case factor_code when 'w21017' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) w21017\n" + "from jp_station_info b left join jp_monitor_real a on(b.mn=a.mn)\n" + "where 1=1 and factor_code in('w00000','w01001','w01018','w21003','w21011',\n" + "    'w21001','w20116','w20117','w20121','w20122',\n" + "    'w20120','w20123','w20115','w01020','w21016','w21017')\n" + "group by mn\n" + "order by mn";
        return getListByEntName(sql,condition,pageInfo);
    }

    @Override
    public List<GasReal> getGasRealList(QueryCondition condition, PageInfo pageInfo) {
        String sql = "select b.name entName,a.mn,DATE_FORMAT(mtime,'%Y-%m-%d %H:%i') mtime,\n" + "max(case factor_code when 'a21026' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a21026,\n" + "max(case factor_code when 'a21026' then CAST(ROUND(calc_avg,2) AS CHAR(20)) else 0 end ) a21026Zs,\n" + "max(case factor_code when 'a21002' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a21002,\n" + "max(case factor_code when 'a21002' then CAST(ROUND(calc_avg,2) AS CHAR(20)) else 0 end ) a21002Zs,\n" + "max(case factor_code when 'a34013' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a34013,\n" + "max(case factor_code when 'a34013' then CAST(ROUND(calc_avg,2) AS CHAR(20)) else 0 end ) a34013Zs,\n" + "max(case factor_code when 'a19001' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a19001,\n" + "max(case factor_code when 'a01012' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a01012,\n" + "max(case factor_code when 'a01013' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a01013,\n" + "max(case factor_code when 'a01011' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a01011,\n" + "max(case factor_code when 'a00000' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a00000,\n" + "max(case factor_code when 'a21005' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a21005,\n" + "max(case factor_code when 'a21005' then CAST(ROUND(calc_avg,2) AS CHAR(20)) else 0 end ) a21005Zs,\n" + "max(case factor_code when 'a21024' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a21024,\n" + "max(case factor_code when 'a21024' then CAST(ROUND(calc_avg,2) AS CHAR(20)) else 0 end ) a21024Zs,\n" + "max(case factor_code when 'a01014' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a01014\n" + "from jp_station_info b left join jp_monitor_real a on(b.mn=a.mn)\n" + "where 1=1\n" + "and factor_code in('a21026','a21026Zs','a21002','a21002Zs','a34013','a34013Zs','a19001',\n" + "'a01012','a01013','a01011','a00000','a21005','a21005Zs','a21024','a21024Zs','a01014')\n" + "group by mn\n" + "order by mn";
        return getListByEntName(sql,condition,pageInfo);
    }

    private List getListByEntName(String sql,QueryCondition condition, PageInfo pageInfo){
        if(!StringUtils.isEmpty(condition.getSortInfo())){
            sql = "select * from ("+sql+") as aa order by "+condition.getSortInfo();
        }
        String entName = null;
        if (condition != null) {
            entName = UtilComm.obj2Str(condition.getConditionMap().get("entName"));
        }
        List groupList = null;
        Object[] params;
        Type[] types;
        if(!StringUtils.isEmpty(entName)){
            sql = sql.replace("1=1","b.name like ? ");
            params = new Object[]{entName};
            types = new Type[]{StringType.INSTANCE};
        }else{
            params = new Object[]{};
            types = new Type[]{};
        }
        try{
            //获取Query配置
            Query query = QueryUtil.getQuery(condition);
            //获取所属的类
            Class<?> objClass = QueryUtil.getClassName(query.getClassName());
            //返回数据
            groupList = this.findMapBySql(sql, query.getCountStr(), pageInfo, params, types, objClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (QueryException e) {
            e.printStackTrace();
        }
        return groupList;
    }
}



/*
#getWaterHourList
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


#getWaterRealList
select b.name entName,a.mn,DATE_FORMAT(mtime,'%Y-%m-%d %H:%i') mtime,
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
from jp_station_info b left join jp_monitor_real a on(b.mn=a.mn)
where 1=1 and factor_code in('w00000','w01001','w01018','w21003','w21011',
    'w21001','w20116','w20117','w20121','w20122',
    'w20120','w20123','w20115','w01020','w21016','w21017')
group by mn
order by mn


#getGasRealList
select b.name entName,a.mn,DATE_FORMAT(mtime,'%Y-%m-%d %H:%i') mtime,
max(case factor_code when 'a21026' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a21026,
max(case factor_code when 'a21026' then CAST(ROUND(calc_avg,2) AS CHAR(20)) else 0 end ) a21026Zs,
max(case factor_code when 'a21002' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a21002,
max(case factor_code when 'a21002' then CAST(ROUND(calc_avg,2) AS CHAR(20)) else 0 end ) a21002Zs,
max(case factor_code when 'a34013' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a34013,
max(case factor_code when 'a34013' then CAST(ROUND(calc_avg,2) AS CHAR(20)) else 0 end ) a34013Zs,
max(case factor_code when 'a19001' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a19001,
max(case factor_code when 'a01012' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a01012,
max(case factor_code when 'a01013' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a01013,
max(case factor_code when 'a01011' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a01011,
max(case factor_code when 'a00000' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a00000,
max(case factor_code when 'a21005' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a21005,
max(case factor_code when 'a21005' then CAST(ROUND(calc_avg,2) AS CHAR(20)) else 0 end ) a21005Zs,
max(case factor_code when 'a21024' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a21024,
max(case factor_code when 'a21024' then CAST(ROUND(calc_avg,2) AS CHAR(20)) else 0 end ) a21024Zs,
max(case factor_code when 'a01014' then CAST(ROUND(val_avg,2) AS CHAR(20)) else 0 end ) a01014
from jp_station_info b left join jp_monitor_real a on(b.mn=a.mn)
where 1=1
and factor_code in('a21026','a21026Zs','a21002','a21002Zs','a34013','a34013Zs','a19001',
'a01012','a01013','a01011','a00000','a21005','a21005Zs','a21024','a21024Zs','a01014')
group by mn
order by mn
* */