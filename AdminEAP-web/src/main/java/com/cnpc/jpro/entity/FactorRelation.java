package com.cnpc.jpro.entity;

import com.cnpc.framework.annotation.Header;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
//@Table(name="jp_factor_relation")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class FactorRelation {

    @Header(name="站点MN")
    @Column(name="mn")
    private String mn;

    @Header(name="名称")
    @Column(name="factor_name")
    private String factorName;

    @Header(name="编码")
    @Column(name="factor_code")
    private String factorCode;

    @Header(name="下限值")
    @Column(name="lower_limit")
    private String lowerLimit;

    @Header(name="上限值")
    @Column(name="upper_limit")
    private String upperLimit;

    @Header(name="排序")
    @Column(name="factor_order")
    private String factorOrder;

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getInfOrder() {
        return factorOrder;
    }

    public void setInfOrder(String factorOrder) {
        this.factorOrder = factorOrder;
    }

    public String getInfName() {
        return factorName;
    }

    public void setInfName(String factorName) {
        this.factorName = factorName;
    }

    public String getInfCode() {
        return factorCode;
    }

    public void setInfCode(String factorCode) {
        this.factorCode = factorCode;
    }

    public String getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(String lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(String upperLimit) {
        this.upperLimit = upperLimit;
    }
}
