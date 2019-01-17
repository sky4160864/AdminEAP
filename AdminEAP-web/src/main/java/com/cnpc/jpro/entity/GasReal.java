package com.cnpc.jpro.entity;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
//@Table(name="jp_water_real")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class GasReal extends BaseEntity {
    private static final long serialVersionUID = 7L;

    @Header(name="企业名称")
    @Column(name="ent_name")
    private String entName;

    @Header(name="设备唯一标识")
    @Column(name="mn")
    private String mn;


    @Header(name="时间")
    @Column(name="mtime")
    private String mtime;

    //二氧化硫 二氧化硫折算值 氮氧化物 氮氧化物折算值 烟尘 烟尘折算值

    @Header(name="二氧化硫")
    @Column(name="a21026")
    private String a21026;

    @Header(name="二氧化硫折算值")
    @Column(name="a21026Zs")
    private String a21026Zs;

    @Header(name="氮氧化物")
    @Column(name="a21002")
    private String a21002;

    @Header(name="氮氧化物折算值")
    @Column(name="a21002Zs")
    private String a21002Zs;

    @Header(name="烟尘")
    @Column(name="a34013")
    private String a34013;

    @Header(name="烟尘折算值")
    @Column(name="a34013Zs")
    private String a34013Zs;

    //氧气含量 烟气温度 烟气压力 流速 流量 一氧化碳 CO折算浓度 氯化氢 HCL折算浓度 烟气湿度

    @Header(name="氧气含量")
    @Column(name="a19001")
    private String a19001;

    @Header(name="烟气温度")
    @Column(name="a01012")
    private String a01012;

    @Header(name="烟气压力")
    @Column(name="a01013")
    private String a01013;

    @Header(name="烟气流速")
    @Column(name="a01011")
    private String a01011;

    @Header(name="废气") //流量
    @Column(name="a00000")
    private String a00000;

    @Header(name="一氧化碳")
    @Column(name="a21005")
    private String a21005;

    @Header(name="CO折算浓度")
    @Column(name="a21005Zs")
    private String a21005Zs;

    @Header(name="氯化氢")
    @Column(name="a21024")
    private String a21024;

    @Header(name="HCL折算浓度")
    @Column(name="a21024Zs")
    private String a21024Zs;

    @Header(name="烟气湿度")
    @Column(name="a01014")
    private String a01014;

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getA21026() {
        return a21026;
    }

    public void setA21026(String a21026) {
        this.a21026 = a21026;
    }

    public String getA21026Zs() {
        return a21026Zs;
    }

    public void setA21026Zs(String a21026Zs) {
        this.a21026Zs = a21026Zs;
    }

    public String getA21002() {
        return a21002;
    }

    public void setA21002(String a21002) {
        this.a21002 = a21002;
    }

    public String getA21002Zs() {
        return a21002Zs;
    }

    public void setA21002Zs(String a21002Zs) {
        this.a21002Zs = a21002Zs;
    }

    public String getA34013() {
        return a34013;
    }

    public void setA34013(String a34013) {
        this.a34013 = a34013;
    }

    public String getA34013Zs() {
        return a34013Zs;
    }

    public void setA34013Zs(String a34013Zs) {
        this.a34013Zs = a34013Zs;
    }

    public String getA19001() {
        return a19001;
    }

    public void setA19001(String a19001) {
        this.a19001 = a19001;
    }

    public String getA01012() {
        return a01012;
    }

    public void setA01012(String a01012) {
        this.a01012 = a01012;
    }

    public String getA01013() {
        return a01013;
    }

    public void setA01013(String a01013) {
        this.a01013 = a01013;
    }

    public String getA01011() {
        return a01011;
    }

    public void setA01011(String a01011) {
        this.a01011 = a01011;
    }

    public String getA00000() {
        return a00000;
    }

    public void setA00000(String a00000) {
        this.a00000 = a00000;
    }

    public String getA21005() {
        return a21005;
    }

    public void setA21005(String a21005) {
        this.a21005 = a21005;
    }

    public String getA21005Zs() {
        return a21005Zs;
    }

    public void setA21005Zs(String a21005Zs) {
        this.a21005Zs = a21005Zs;
    }

    public String getA21024() {
        return a21024;
    }

    public void setA21024(String a21024) {
        this.a21024 = a21024;
    }

    public String getA21024Zs() {
        return a21024Zs;
    }

    public void setA21024Zs(String a21024Zs) {
        this.a21024Zs = a21024Zs;
    }

    public String getA01014() {
        return a01014;
    }

    public void setA01014(String a01014) {
        this.a01014 = a01014;
    }
}
