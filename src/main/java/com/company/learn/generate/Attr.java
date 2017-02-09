package com.company.learn.generate;

public class Attr {

    private String field;  //字段名
    private String type; //字段类型
    private String comment; //注释

    public Attr(String field, String type, String comment) {
        this.field = field;
        this.type = type;
        this.comment = comment;
    }

    public Attr() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
