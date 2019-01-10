package com.cnpc.jpro.service.impl;

import com.cnpc.framework.base.pojo.PageInfo;
import com.cnpc.framework.base.service.impl.BaseServiceImpl;
import com.cnpc.framework.query.entity.QueryCondition;
import com.cnpc.jpro.service.MonitorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Huang Jianhai on 2019/1/10.
 */
@Service("monitorService")
public class MonitorServiceImpl extends BaseServiceImpl implements MonitorService {
    @Override
    public List getGroupList(QueryCondition condition, PageInfo pageInfo) {
        return null;
    }
}
