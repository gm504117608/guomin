package com.company.learn.excel.excelImport2.exception;

/**
 * 导出时的异常
 */
public class ExcelExportException extends RuntimeException {

    private static final long serialVersionUID = 8036129856262757376L;

    private String msg;

    public ExcelExportException() {
    }

    public ExcelExportException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ExcelExportException(String msg, Throwable tx) {
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