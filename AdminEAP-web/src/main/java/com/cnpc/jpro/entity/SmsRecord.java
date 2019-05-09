package com.cnpc.jpro.entity;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="jp_sms_record")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class SmsRecord extends BaseEntity {
    private static final long serialVersionUID = 7L;

    @Header(name="类型")
    @Column(name="sms_type")
    private String smsType;

    @Header(name = "时间")
    @Column(name = "sms_time")
    private Date smsTime;

    @Header(name = "关联ID")
    @Column(name = "sms_rid")
    private String smsRid;

    @Header(name="短信内容")
    @Column(name="sms_content")
    private String smsContent;

    @Header(name="企业")
    @Column(name="station_name")
    private String stationName;

    @Header(name="联系人")
    @Column(name="sms_link")
    private String smsLink;

    @Header(name="号码")
    @Column(name="telephone")
    private String telephone;

    @Header(name="状态")
    @Column(name="sms_status")
    private String smsStatus;

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getSmsLink() {
        return smsLink;
    }

    public void setSmsLink(String smsLink) {
        this.smsLink = smsLink;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus;
    }

    public Date getSmsTime() {
        return smsTime;
    }

    public void setSmsTime(Date smsTime) {
        this.smsTime = smsTime;
    }

    public String getSmsRid() {
        return smsRid;
    }

    public void setSmsRid(String smsRid) {
        this.smsRid = smsRid;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
