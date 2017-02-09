package com.company.learn.excel.excelImport2.exception;

/**
 * Created by dell on 2017/1/14.
 */
public class ExcelGetConfigException extends RuntimeException {

    private static final long serialVersionUID = 54545856262757376L;

    private String msg;

    public ExcelGetConfigException() {
    }

    public ExcelGetConfigException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ExcelGetConfigException(String msg, Throwable tx) {
        super(msg, tx);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
