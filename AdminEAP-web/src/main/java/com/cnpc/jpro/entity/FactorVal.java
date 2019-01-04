package com.cnpc.jpro.entity;

import com.cnpc.framework.annotation.Header;
import com.cnpc.framework.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="jp_factor_val")
//表B.1 水监测因子编码表（引用HJ 525-2009）
public class FactorVal extends BaseEntity {
    @Header(name="")
    @Column(name="cou")
    private String cou;


    @Header(name="编码")
    @Column(name="code")
    private String code;

    @Header(name="中文名称")
    @Column(name="name_zh")
    private String nameZh;

    @Header(name="原编码")
    @Column(name="code_old")
    private String codeOld;

    @Header(name="缺省计量单位（浓度）")
    @Column(name="unit_con")
    private String unitCon;

    @Header(name="缺省计量单位（排放量）")
    @Column(name="unit_em")
    private String unitEm;

    @Header(name="缺省数据类型")
    @Column(name="data_type")
    private String dataType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getCodeOld() {
        return codeOld;
    }

    public void setCodeOld(String codeOld) {
        this.codeOld = codeOld;
    }

    public String getUnitCon() {
        return unitCon;
    }

    public void setUnitCon(String unitCon) {
        this.unitCon = unitCon;
    }

    public String getUnitEm() {
        return unitEm;
    }

    public void setUnitEm(String unitEm) {
        this.unitEm = unitEm;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
