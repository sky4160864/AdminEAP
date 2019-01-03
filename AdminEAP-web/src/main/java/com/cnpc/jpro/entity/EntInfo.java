package com.cnpc.jpro.entity;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.base.entity.BaseEntity;
import com.cnpc.framework.base.entity.Dict;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="jp_ent_info")
public class EntInfo extends BaseEntity {

    @Header(name="企业名称")
    @Column(name="name")
    private String name;


    @Header(name="地址")
    @Column(name="address")
    private String address;

    @Header(name="经度")
    @Column(name="lat")
    private String lat;

    @Header(name="纬度")
    @Column(name="lng")
    private String lng;

    @Header(name="地区",joinClass = Dict.class,dataSource="330000") //只能取下一级
    @Column(name="area_code")
    private String areaCode;


    @Header(name="备注")
    @Column(name="remark")
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
