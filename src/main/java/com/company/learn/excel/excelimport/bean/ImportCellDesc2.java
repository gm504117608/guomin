package com.company.learn.excel.excelimport.bean;

import com.company.learn.excel.excelimport.bean.validate.CellValidation;
import com.company.learn.excel.excelimport.bean.validate.Validation;
import com.company.learn.excel.excelimport.util.StringUtil;
import org.apache.poi.hssf.util.CellReference;

import java.util.ArrayList;
import java.util.List;

/**
 * 单元格的描述信息
 * <p/>
 * （1）数据格式校验时，可以精确定位到某个单元格。
 */
public class ImportCellDesc2 {
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
     * 字段值
     */
    private String fieldValue;
    /**
     * 字段的校验器
     */
    private CellValidation cellValidation = null;

    public ImportCellDesc2() {
    }

    public ImportCellDesc2(ImportCellDesc2 o) {
        if (o != null) {
            this.cellRef = o.getCellRef();
            this.fieldName = o.getFieldName();
            this.fieldValue = o.getFieldValue();
            this.cellValidation = o.getCellValidation();
        }
    }

    public ImportCellDesc2(String cellRef, String fieldName, String fieldValue) {
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
        if (cellValidation != null) {
            sb.append("\tvalidator : [ ");
            sb.append(cellValidation.getCellName());
            sb.append(cellValidation.getCellValidate());
            sb.append(" ]");
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

    public CellValidation getCellValidation() {
        return cellValidation;
    }

    public void setCellValidation(CellValidation cellValidation) {
        this.cellValidation = cellValidation;
    }

    /**
     * 为单元格元素增加校验器
     * @param validator 校验器类的路径
     */
    public void addValidator(String validator) {
        if (StringUtil.isEmpty(validator)) {
            return;
        }
        List<Validation> list = null;
        if (cellValidation == null) {
            cellValidation = new CellValidation();
            list = new ArrayList<Validation>();
            cellValidation.setCellName(cellRef);
        }else{
            list = cellValidation.getCellValidate();
        }
        Validation v = new Validation(validator, null, null, null);
        list.add(v);
        cellValidation.setCellValidate(list);
    }
}