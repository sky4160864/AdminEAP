package com.cnpc.jpro.entity;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.base.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="jp_st_code")
public class StCode extends BaseEntity {

    @Header(name="系统名称")
    @Column(name="name")
    private String name;


    @Header(name="系统编号")
    @Column(name="code")
    private String code;

    @Header(name="是否启用")
    @Column(name="enable")
    private Boolean enable;

    @Header(name="备注")
    @Column(name="remark")
    private String remark;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
