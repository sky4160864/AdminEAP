package com.cnpc.jpro.service;

import com.cnpc.framework.base.pojo.PageInfo;
import com.cnpc.framework.base.service.BaseService;
import com.cnpc.framework.query.entity.QueryCondition;
import com.cnpc.jpro.entity.GasReal;
import com.cnpc.jpro.entity.WaterHour;

import java.util.List;

/**
 * Created by Huang Jianhai on 2019/1/10.
 */

public interface MonitorService extends BaseService {

    List<WaterHour> getWaterHourList(QueryCondition condition, PageInfo pageInfo);

    List<WaterHour> getWaterRealList(QueryCondition condition, PageInfo pageInfo);

    List<GasReal> getGasRealList(QueryCondition condition, PageInfo pageInfo);
}
