package com.company.learn.excel.excelImport2.bean;

import com.company.learn.excel.excelImport2.bean.validate.Validator;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel导入xml描述文件的结构
 */
public class ExcelStruct {
    /**
     * 离散数据的单元格描述信息
     */
    private List<ImportCellDesc> onceImportCells = null;
    /**
     * 列表数据的单元格描述信息
     */
    private List<ImportCellDesc> repeatImportCells = null;
    /**
     * 列表数据开始的行号 默认从第二行开始（从0开始计数）
     */
    private int startLine = 1;
    /**
     * 列表数据结束的行号 （从0开始计数）
     */
    private int endLine;
    /**
     * 最多可以读取多少行数据 默认1000行
     */
    private int importNumber = 1000;
    /**
     * 是否进行规则校验，默认不进行校验（true 校验；false 不校验）
     */
    private boolean ruleCheck = false;
    /**
     * xml中定义的校验器
     */
    private List<Validator> validatorList = null;

    public ExcelStruct() {
        this.onceImportCells = new ArrayList<ImportCellDesc>();
        this.repeatImportCells = new ArrayList<ImportCellDesc>();
    }

    public ExcelStruct(List<ImportCellDesc> onceImportCells, List<ImportCellDesc> repeatImportCells, int startLine,
                       int endLine, boolean ruleCheck, int importNumber) {
        this.onceImportCells = onceImportCells;
        this.repeatImportCells = repeatImportCells;
        this.endLine = endLine;
        this.startLine = startLine;
        this.ruleCheck = ruleCheck;
        this.importNumber = importNumber;
    }

    public List<ImportCellDesc> getOnceImportCells() {
        return onceImportCells;
    }

    public void setOnceImportCells(List<ImportCellDesc> onceImportCells) {
        this.onceImportCells = onceImportCells;
    }

    public List<ImportCellDesc> getRepeatImportCells() {
        return repeatImportCells;
    }

    public void setRepeatImportCells(List<ImportCellDesc> repeatImportCells) {
        this.repeatImportCells = repeatImportCells;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public List<Validator> getValidatorList() {
        return validatorList;
    }

    public void setValidatorList(List<Validator> validatorList) {
        this.validatorList = validatorList;
    }

    public boolean isRuleCheck() {
        return ruleCheck;
    }

    public void setRuleCheck(boolean ruleCheck) {
        this.ruleCheck = ruleCheck;
    }

    public int getImportNumber() {
        return importNumber;
    }

    public void setImportNumber(int importNumber) {
        this.importNumber = importNumber;
    }

    /**
     * 添加自定义的校验器
     *
     * @param name  校验器名称
     * @param value 校验器的类的路径
     */
    public void addValidator(String name, String value) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value)) {
            return;
        }
        if (null == this.validatorList) {
            this.validatorList = new ArrayList<Validator>();
        }
        Validator v = new Validator(name, value);
        this.validatorList.add(v);
    }

    /**
     * 获取自定义的校验类信息的类路径（package路径）
     * @param name 自定义类name
     * @return
     */
    public String findValidatorPackagePath(String name){
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for(Validator v : this.validatorList){
            if(name.equals(v.getName())){
                return v.getValue();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ExcelStruct{" +
                "onceImportCells=" + onceImportCells +
                ", repeatImportCells=" + repeatImportCells +
                ", startLine=" + startLine +
                ", endLine=" + endLine +
                ", ruleCheck=" + ruleCheck +
                ", importNumber=" + importNumber +
                ", validatorList=" + validatorList +
                '}';
    }
}