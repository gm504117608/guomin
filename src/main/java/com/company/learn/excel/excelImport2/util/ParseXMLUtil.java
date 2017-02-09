package com.company.learn.excel.excelImport2.util;

import com.company.learn.excel.excelImport2.bean.ExcelStruct;
import com.company.learn.excel.excelImport2.bean.ImportCellDesc;
import com.company.learn.excel.excelImport2.bean.validate.Validation;
import com.company.learn.excel.excelImport2.exception.ExcelGetConfigException;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析导入Excel的XML描述文件
 */
public class ParseXMLUtil {
    private ParseXMLUtil() {
    }

    /**
     * 根据给定的XML文件解析出Excel的结构
     *
     * @param xmlFile xml文件路径
     * @return
     */
    public static ExcelStruct parseImportStruct(String xmlFile) {
        if (StringUtils.isEmpty(xmlFile)) {
            return null;
        }
        InputStream is = null;
        ExcelStruct excelStruct = null;
        SAXBuilder saxBuilder = null;
        Document document = null;
        try {
            is = new FileInputStream(xmlFile);
            if (null == is) {
                throw new FileNotFoundException("Excel的描述文件 : " + xmlFile + " 未找到.");
            }
            saxBuilder = new SAXBuilder(false);
            document = saxBuilder.build(is);
            // 根节点
            Element root = document.getRootElement();
            // 一次导入
            List onceList = root.getChildren("onceImport");
            // 重复导入
            List repeatList = root.getChildren("repeatImport");
            // 校验器的定义
            List validators = root.getChildren("validators");
            // 是否校验规则
            Element ruleCheck = root.getChild("rule-check");
            //  最多可以读取多少行数据 默认1000行
            Element importNumberEle = root.getChild("import-number");
            int importNumber = 1000;
            try {
                importNumber = Integer.parseInt(importNumberEle.getTextTrim() == null ? "1000" : importNumberEle.getTextTrim());
            } catch (NumberFormatException e) {
                throw new ExcelGetConfigException("获取重复数据导入量（配置为：import-number）出现错误，只能设置为正整数。");
            }
            excelStruct = new ExcelStruct();
            // 读取校验器配置
            excelStruct.setRuleCheck(Boolean.parseBoolean(ruleCheck.getTextTrim()));
            excelStruct.setImportNumber(importNumber);

            parseValidatorConfig(excelStruct, validators);
            parseOnceImport(excelStruct, onceList);
            parseRepeatImport(excelStruct, repeatList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcelGetConfigException("Excel的xml配置文件 : " + xmlFile + " 解析出现错误.");
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return excelStruct;
    }

    /**
     * 读取校验器的相关配置
     *
     * @param excelStruct Excel导入描述文件的结构对象
     * @param validators  Excel导入校验器
     */
    private static void parseValidatorConfig(ExcelStruct excelStruct, List validators) {
        if (null == excelStruct || null == validators || validators.size() <= 0) {
            return;
        }
        // 读取校验器的定义
        Element validElem = (Element) validators.get(0);
        if (null == validElem) {
            return;
        }
        List validatorList = validElem.getChildren("validator");
        if (null == validatorList || validatorList.size() <= 0) {
            return;
        }
        for (Object obj : validatorList) {
            if (null == obj) {
                continue;
            }
            Element validator = (Element) obj;
            String name = validator.getAttributeValue("name");
            String value = validator.getAttributeValue("value");
            excelStruct.addValidator(name, value);
        }
    }

    /**
     * 解析列表数据导入
     *
     * @param excelStruct Excel导入描述文件的结构对象
     * @param repeatList  列表数据导入对象
     */
    private static void parseRepeatImport(ExcelStruct excelStruct, List repeatList) {
        if (null == excelStruct || null == repeatList || repeatList.size() <= 0) {
            return;
        }
        Element onceElem = (Element) repeatList.get(0);
        // 读取开始、终止行
        int startLine = 1;
        int endLine;
        try {
            startLine = Integer.parseInt(((Element) onceElem.getChild("start-line")).getTextTrim());
            endLine = Integer.parseInt(((Element) onceElem.getChild("end-line")).getTextTrim());
        } catch (IndexOutOfBoundsException e) {
            throw new ExcelGetConfigException("请在XML描述文件中添加<start-line/>、<end-line/>配置项!");
        }
        excelStruct.setStartLine(startLine);
        excelStruct.setEndLine(endLine);
        // 遍历每一个单元格的描述信息
        List cellDescList = onceElem.getChildren("cellDesc");
        if (null == cellDescList || cellDescList.size() <= 0) {
            return;
        }
        List<ImportCellDesc> repeatImportCells = new ArrayList<ImportCellDesc>();
        getImportCellDesc(excelStruct, cellDescList, repeatImportCells);
        excelStruct.setRepeatImportCells(repeatImportCells);
    }

    /**
     * 解析离散数据导入
     *
     * @param excelStruct Excel导入描述文件的结构对象
     * @param onceList    离散数据导入对象
     */
    private static void parseOnceImport(ExcelStruct excelStruct, List onceList) {
        if (null == onceList || onceList.size() <= 0) {
            return;
        }
        Element onceElem = (Element) onceList.get(0);
        List cellDescList = onceElem.getChildren("cellDesc");
        if (null == cellDescList || cellDescList.size() <= 0) {
            return;
        }
        List<ImportCellDesc> onceImportCells = new ArrayList<ImportCellDesc>();
        // 遍历每一个单元格的描述信息
        getImportCellDesc(excelStruct, cellDescList, onceImportCells);
        excelStruct.setOnceImportCells(onceImportCells);
    }

    /**
     * 获取含有每个单元格信息、校验信息的集合list
     *
     * @param excelStruct  Excel导入描述文件的结构对象
     * @param cellDescList 单元格描述信息集合
     * @param list
     */
    private static void getImportCellDesc(ExcelStruct excelStruct, List cellDescList, List<ImportCellDesc> list) {
        if (null == cellDescList || cellDescList.size() <= 0) {
            return;
        }
        // 遍历每一个单元格的描述信息
        ImportCellDesc cellDesc = null;
        for (Object obj : cellDescList) {
            if (null == obj) {
                continue;
            }
            Element cellElem = (Element) obj;
            String cellRef = ((Element) cellElem.getChild("cellRef")).getTextTrim();
            String fieldName = ((Element) cellElem.getChild("fieldName")).getTextTrim();
            cellDesc = new ImportCellDesc();
            cellDesc.setCellRef(cellRef.toUpperCase());
            cellDesc.setFieldName(fieldName);
            Element cellValidatorEle = cellElem.getChild("cell-validator");
            if(null != cellValidatorEle){
                getCellValidator(excelStruct, cellDesc, cellValidatorEle);
            }
            list.add(cellDesc);
        }
    }

    /**
     * 获取含有每个单元格校验信息
     */
    private static void getCellValidator(ExcelStruct excelStruct, ImportCellDesc cellDesc, Element cellValidatorEle){
        if(null == cellValidatorEle){
            return;
        }
        List validatorList = cellValidatorEle.getChildren("validator");
        if(null == validatorList || validatorList.size() <= 0){
            return;
        }
        Validation v = null;
        List<Validation> list = new ArrayList<Validation>();
        for (Object obj : validatorList) {
            if (null == obj) {
                continue;
            }
            Element validator = (Element) obj;
            String name = validator.getAttributeValue("name");
            if(StringUtils.isNotEmpty(name)){
                name = excelStruct.findValidatorPackagePath(name);
            }else{
                throw new ExcelGetConfigException("获取校验数据的<validator />配置的name属性必须填写。");
            }
            String dateFormat = validator.getAttributeValue("dateFormat");
            String containStr = validator.getAttributeValue("containStr");
            int minLength;
            int maxLength;
            try {
                minLength = Integer.parseInt(validator.getAttributeValue("minLength") == null ?
                        "0" : validator.getAttributeValue("minLength"));
                maxLength = Integer.parseInt(validator.getAttributeValue("maxLength") == null ?
                        "0" : validator.getAttributeValue("maxLength"));
            } catch (NumberFormatException e) {
                throw new ExcelGetConfigException("获取校验数据长度（配置为：minLength、maxLength）出现错误，只能设置为正整数。");
            }
            v = new Validation(name, minLength, maxLength, dateFormat, containStr);
            list.add(v);
        }
        cellDesc.setValidationList(list);
    }
}