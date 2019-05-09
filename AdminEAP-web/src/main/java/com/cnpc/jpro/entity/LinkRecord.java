package com.cnpc.jpro.entity;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="jp_link_record")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class LinkRecord extends BaseEntity {
    private static final long serialVersionUID = 7L;

    @Header(name="企业ID")
    @Column(name="station_id")
    private String stationId;

    @Header(name="联系人")
    @Column(name="sms_link")
    private String smsLink;

    @Header(name="号码")
    @Column(name="telephone")
    private String telephone;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
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
}
