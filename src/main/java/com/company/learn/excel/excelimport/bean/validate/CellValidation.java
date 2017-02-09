package com.company.learn.excel.excelimport.bean.validate;

import java.util.List;

/**
 * Excel导入的单元格校验信息封装
 */
public class CellValidation {

    /**
     * 引用的单元格；如：A3
     */
    private String cellName;
    /**
     * 单元格校验信息
     */
    private List<Validation> cellValidate;


    public CellValidation(){}

    public CellValidation(String cellName, List<Validation> cellValidate){
        this.cellName = cellName;
        this.cellValidate = cellValidate;
    }

    public CellValidation(CellValidation cv){
        if(null != cv){
            this.cellName = cv.getCellName();
            this.cellValidate = cv.getCellValidate();
        }
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public List<Validation> getCellValidate() {
        return cellValidate;
    }

    public void setCellValidate(List<Validation> cellValidate) {
        this.cellValidate = cellValidate;
    }

    @Override
    public String toString() {
        return "CellValidation{" +
                "cellName='" + cellName + '\'' +
                ", cellValidate=" + cellValidate +
                '}';
    }
}
