package com.cnpc.jpro.entity;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="jp_factor_rule")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class FactorRule extends BaseEntity {
    private static final long serialVersionUID = 7L;

    @Header(name="企业名称",joinClass = StationInfo.class)
    @Column(name = "station_id")
    private String stationId;

    @Header(name="规则名称")
    @Column(name="rule_name")
    private String ruleName;

    @Header(name="规则内容")
    @Column(name="rule_content")
    private String ruleContent;

    @Header(name="规则描述")
    @Column(name="rule_desc")
    private String ruleDesc;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleContent() {
        return ruleContent;
    }

    public void setRuleContent(String ruleContent) {
        this.ruleContent = ruleContent;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }
}
