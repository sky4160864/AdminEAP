package com.cnpc.jpro.pojo;

/**
 * author captain.Huang 2019/5/6.
 */
public class LayuiTableCol {
    // {field: 'get_time', title: '时间',fixed: true, width: 180}
    String field;
    String title;
    boolean fixed;
    int width;

    public LayuiTableCol(){}

    public LayuiTableCol(String field,String title,boolean fixed,int width){
        this.field =field;
        this.title=title;
        this.fixed=fixed;
        this.width=width;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
