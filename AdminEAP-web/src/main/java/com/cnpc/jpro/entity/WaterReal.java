package com.cnpc.jpro.entity;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@Table(name="jp_water_real")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class WaterReal extends BaseEntity {
    private static final long serialVersionUID = 5L;

    //查询
    private String btime;
    private String etime;

    @Header(name="企业名称")
    @Column(name="ent_name")
    private String entName;

    @Header(name="设备唯一标识")
    @Column(name="mn")
    private String mn;


    @Header(name="时间")
    @Column(name="mtime")
    private String mtime;

    //化学需氧量 PH值 氨氮 总磷 总氮 废水瞬时流量 总铬 六价铬 总镍 总铜 总铅 总锌 总镉 总有机碳 氰化物 氟离子

    @Header(name="污水")
    @Column(name="w00000")
    private String w00000;

    @Header(name="PH值")
    @Column(name="w01001")
    private String w01001;

    @Header(name="化学需氧量")
    @Column(name="w01018")
    private String w01018;

    @Header(name="氨氮")
    @Column(name="w21003")
    private String w21003;

    @Header(name="总磷")
    @Column(name="w21011")
    private String w21011;

    @Header(name="总氮")
    @Column(name="w21001")
    private String w21001;

    @Header(name="总铬")
    @Column(name="w20116")
    private String w20116;

    @Header(name="六价铬")
    @Column(name="w20117")
    private String w20117;

    @Header(name="总镍")
    @Column(name="w20121")
    private String w20121;

    @Header(name="总铜")
    @Column(name="w20122")
    private String w20122;

    @Header(name="总铅")
    @Column(name="w20120")
    private String w20120;

    @Header(name="总锌")
    @Column(name="w20123")
    private String w20123;

    @Header(name="总镉")
    @Column(name="w20115")
    private String w20115;

    @Header(name="总有机碳")
    @Column(name="w01020")
    private String w01020;

    @Header(name="氰化物")
    @Column(name="w21016")
    private String w21016;

    @Header(name="氟化物")
    @Column(name="w21017")
    private String w21017;

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

    public String getW00000() {
        return w00000;
    }

    public void setW00000(String w00000) {
        this.w00000 = w00000;
    }

    public String getW01001() {
        return w01001;
    }

    public void setW01001(String w01001) {
        this.w01001 = w01001;
    }

    public String getW01018() {
        return w01018;
    }

    public void setW01018(String w01018) {
        this.w01018 = w01018;
    }

    public String getW21003() {
        return w21003;
    }

    public void setW21003(String w21003) {
        this.w21003 = w21003;
    }

    public String getW21011() {
        return w21011;
    }

    public void setW21011(String w21011) {
        this.w21011 = w21011;
    }

    public String getW21001() {
        return w21001;
    }

    public void setW21001(String w21001) {
        this.w21001 = w21001;
    }

    public String getW20116() {
        return w20116;
    }

    public void setW20116(String w20116) {
        this.w20116 = w20116;
    }

    public String getW20117() {
        return w20117;
    }

    public void setW20117(String w20117) {
        this.w20117 = w20117;
    }

    public String getW20121() {
        return w20121;
    }

    public void setW20121(String w20121) {
        this.w20121 = w20121;
    }

    public String getW20122() {
        return w20122;
    }

    public void setW20122(String w20122) {
        this.w20122 = w20122;
    }

    public String getW20120() {
        return w20120;
    }

    public void setW20120(String w20120) {
        this.w20120 = w20120;
    }

    public String getW20123() {
        return w20123;
    }

    public void setW20123(String w20123) {
        this.w20123 = w20123;
    }

    public String getW20115() {
        return w20115;
    }

    public void setW20115(String w20115) {
        this.w20115 = w20115;
    }

    public String getW01020() {
        return w01020;
    }

    public void setW01020(String w01020) {
        this.w01020 = w01020;
    }

    public String getW21016() {
        return w21016;
    }

    public void setW21016(String w21016) {
        this.w21016 = w21016;
    }

    public String getW21017() {
        return w21017;
    }

    public void setW21017(String w21017) {
        this.w21017 = w21017;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }
}
