package com.company.learn.excel.excelimport.bean;

import com.company.learn.excel.excelimport.bean.validate.CellValidation;
import com.company.learn.excel.excelimport.bean.validate.Validation;
import com.company.learn.excel.excelimport.bean.validate.Validator;
import com.company.learn.excel.excelimport.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel导入描述文件的结构
 */
public class ExcelStruct2 {
    /**
     * 一次导入的单元格描述信息
     */
    private List<ImportCellDesc2> onceImportCells = null;
    /**
     * 重复导入的单元格描述信息
     */
    private List<ImportCellDesc2> repeatImportCells = null;
    /**
     * 重复开始的编码，如：START
     */
    private String startCode;
    /**
     * 重复结束的编码，如：END
     */
    private String endCode;
    /**
     * 是否进行规则校验，默认不进行校验
     */
    private boolean ruleCheck = false;
    /**
     * xml中定义的校验器
     */
    private List<Validator> sysValidatorList = null;
    /**
     * 单元格对应的校验器
     */
    private List<CellValidation> cellValidationList = null;

    public ExcelStruct2() {
        this.onceImportCells = new ArrayList<ImportCellDesc2>();
        this.repeatImportCells = new ArrayList<ImportCellDesc2>();
    }

    public ExcelStruct2(List<ImportCellDesc2> onceImportCells, List<ImportCellDesc2> repeatImportCells,
                        String endCode, String startCode, boolean ruleCheck) {
        this.onceImportCells = onceImportCells;
        this.repeatImportCells = repeatImportCells;
        this.endCode = endCode;
        this.startCode = startCode;
        this.ruleCheck = ruleCheck;
    }

    public List<ImportCellDesc2> getOnceImportCells() {
        return onceImportCells;
    }

    public void setOnceImportCells(List<ImportCellDesc2> onceImportCells) {
        this.onceImportCells = onceImportCells;
    }

    public List<ImportCellDesc2> getRepeatImportCells() {
        return repeatImportCells;
    }

    public void setRepeatImportCells(List<ImportCellDesc2> repeatImportCells) {
        this.repeatImportCells = repeatImportCells;
    }

    public String getEndCode() {
        return endCode;
    }

    public void setEndCode(String endCode) {
        this.endCode = endCode;
    }

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
    }

    public List<Validator> getSysValidatorList() {
        return sysValidatorList;
    }

    public void setSysValidatorList(List<Validator> sysValidatorList) {
        this.sysValidatorList = sysValidatorList;
    }

    public List<CellValidation> getCellValidationList() {
        return cellValidationList;
    }

    public void setCellValidationList(List<CellValidation> cellValidationList) {
        this.cellValidationList = cellValidationList;
    }

    public boolean isRuleCheck() {
        return ruleCheck;
    }

    public void setRuleCheck(boolean ruleCheck) {
        this.ruleCheck = ruleCheck;
    }

    /**
     * 添加校验器定义
     *
     * @param name  校验器名称
     * @param value 校验器的类全称
     */
    public void addSysValidator(String name, String value) {
        if (StringUtil.isEmpty(name) || StringUtil.isEmpty(value)) {
            return;
        }
        if (this.sysValidatorList == null) {
            this.sysValidatorList = new ArrayList<Validator>();
        }
        Validator v = new Validator(name, value);
        this.sysValidatorList.add(v);
    }

    /**
     * 给单元格添加校验器
     *
     * @param cellName  单元格名称
     * @param validator 校验器名称
     * @param maxLength 长度最大值
     * @param minLength 长度最小值
     * @param dateFormat 日期格式
     */
    public void addCellValidator(String cellName, String validator, String maxLength, String minLength, String dateFormat) {
        if (StringUtil.isEmpty(cellName) || StringUtil.isEmpty(validator)) {
            return;
        }
        if (this.cellValidationList == null) {
            this.cellValidationList = new ArrayList<CellValidation>();
        }
        cellName = cellName.toUpperCase();
        CellValidation cv = findRecordByCellName(cellName);
        List<Validation> list = new ArrayList<Validation>();
        if (cv == null) {
            cv = new CellValidation(cellName, list);
            this.cellValidationList.add(cv);
        }else{
            list = cv.getCellValidate();
        }
        String validatorPath = findValidatorPackagePath(validator);
        if(sysValidatorList != null && StringUtil.isNotEmpty(validatorPath)){
            Validation v = new Validation(validatorPath, minLength, maxLength, dateFormat);
            list.add(v);
        }
    }

    /**
     * 判断该单元格是否已经存在校验信息
     * @param cellName 单元格名称
     * @return
     */
    public CellValidation findRecordByCellName(String cellName){
        if (StringUtil.isEmpty(cellName)) {
            return null;
        }
        for(CellValidation cv : this.cellValidationList){
            if(cellName.equals(cv.getCellName())){
                return cv;
            }
        }
        return null;

    }

    /**
     * 获取自定义的校验类信息的类路径（package路径）
     * @param validator 自定义类name
     * @return
     */
    private String findValidatorPackagePath(String validator){
        if (StringUtil.isEmpty(validator)) {
            return null;
        }
        for(Validator v : this.sysValidatorList){
            if(validator.equals(v.getName())){
                return v.getValue();
            }
        }
        return null;

    }

    @Override
    public String toString() {
        return "ExcelExportStruct{" +
                "onceImportCells=" + onceImportCells +
                ", repeatImportCells=" + repeatImportCells +
                ", startCode='" + startCode + '\'' +
                ", ruleCheck='" + ruleCheck + '\'' +
                ", sysValidatorList=" + sysValidatorList +
                ", cellValidationList=" + cellValidationList +
                '}';
    }
}