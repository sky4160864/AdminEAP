package com.cnpc.jpro.pojo;

public class FactorRelation2 {
    private String mn;

    private String factor_name;

    private String factor_code;

    private String factor_unit;

    private String lower_limit;

    private String upper_limit;

    private int factor_order;

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getFactor_name() {
        return factor_name;
    }

    public void setFactor_name(String factor_name) {
        this.factor_name = factor_name;
    }

    public String getFactor_code() {
        return factor_code;
    }

    public void setFactor_code(String factor_code) {
        this.factor_code = factor_code;
    }

    public String getLower_limit() {
        return lower_limit;
    }

    public void setLower_limit(String lower_limit) {
        this.lower_limit = lower_limit;
    }

    public String getUpper_limit() {
        return upper_limit;
    }

    public void setUpper_limit(String upper_limit) {
        this.upper_limit = upper_limit;
    }

    public int getFactor_order() {
        return factor_order;
    }

    public void setFactor_order(int factor_order) {
        this.factor_order = factor_order;
    }

    public String getFactor_unit() {
        return factor_unit;
    }

    public void setFactor_unit(String factor_unit) {
        this.factor_unit = factor_unit;
    }
}
