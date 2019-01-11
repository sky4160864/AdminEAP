package com.cnpc.jpro.service;

import com.cnpc.framework.base.pojo.PageInfo;
import com.cnpc.framework.base.service.BaseService;
import com.cnpc.framework.query.entity.QueryCondition;
import com.cnpc.jpro.entity.WaterHour;

import java.util.List;

/**
 * Created by Huang Jianhai on 2019/1/10.
 */

public interface MonitorService extends BaseService {
    /**
     * 获取用户组列表的接口
     *
     * @param condition 查询条件  name (分组名称或type:code)
     * @param pageInfo  分页信息
     * @return
     */
    List<WaterHour> getWaterHourList(QueryCondition condition, PageInfo pageInfo);
}
