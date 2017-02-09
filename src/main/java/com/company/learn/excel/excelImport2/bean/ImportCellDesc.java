package com.company.learn.excel.excelImport2.bean;

import com.company.learn.excel.excelImport2.bean.validate.Validation;
import org.apache.poi.hssf.util.CellReference;

import java.util.ArrayList;
import java.util.List;

/**
 * 每个单元格的描述信息
 * 数据格式校验时，可以精确定位到某个单元格。
 */
public class ImportCellDesc {
    /**
     * 引用的单元格；如：A3
     */
    private String cellRef;
    /**
     * 单元格的对应数据库的字段名称；
     * 如：fieldName = "username"
     */
    private String fieldName;
    /**
     * 单元格的值
     */
    private String fieldValue;
    /**
     * 单元格对应的校验器信息
     */
    private List<Validation> validationList = null;

    public ImportCellDesc() {
    }

    public ImportCellDesc(ImportCellDesc o) {
        if (o != null) {
            this.cellRef = o.getCellRef();
            this.fieldName = o.getFieldName();
            this.fieldValue = o.getFieldValue();
            this.validationList = o.getValidationList();
        }
    }

    public ImportCellDesc(String cellRef, String fieldName, String fieldValue) {
        this.cellRef = cellRef;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    /**
     * 返回单元格的行标（从1开始）
     * cellRef 如：B5 返回行坐标为 5
     * @return 5
     */
    public int getCellRow() {
        CellReference ref = new CellReference(cellRef);
        return ref.getRow() + 1;
    }

    /**
     * 返回单元格的列标（从1开始）
     * cellRef 如：B5 返回列坐标为 2
     * @return 2
     */
    public int getCellCol() {
        CellReference ref = new CellReference(cellRef);
        return ref.getCol() + 1;
    }

    /**
     * 输出ImportCellDesc对象的信息
     * @return 返回ImportCellDesc对象内容信息
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(1000);
        if (validationList != null) {
            for(Validation v : validationList){
                sb.append("\tValidation : [ ");
                sb.append(v.getName());
                sb.append(";");
                sb.append(v.getMaxLength());
                sb.append(";");
                sb.append(v.getMinLength());
                sb.append(";");
                sb.append(v.getDateFormat());
                sb.append(";");
                sb.append(v.getContainStr());
                sb.append(" ]");
            }
        }
        return getCellRef() + "\t(" + getFieldName() + " : " + getFieldValue() + ")" + sb.toString();
    }

    public String getCellRef() {
        return cellRef;
    }

    public void setCellRef(String cellRef) {
        this.cellRef = cellRef;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public List<Validation> getValidationList() {
        return validationList;
    }

    public void setValidationList(List<Validation> validationList) {
        this.validationList = validationList;
    }

    /**
     * 为单元格增加校验信息
     * @param validation 单元格校验信息封装
     */
    public void addValidator(Validation validation) {
        if (null == validation) {
            return;
        }
        if (null == validationList) {
            validationList = new ArrayList<Validation>();
        }
        validationList.add(validation);
    }
}