package com.company.learn.excel.excelExport2.util;

import com.company.learn.excel.excelImport2.exception.ExcelExportException;
import com.company.learn.excel.excelImport2.exception.ExcelGetConfigException;
import com.company.learn.excel.excelExport2.module.bean.ExcelExportStruct;
import com.company.learn.excel.excelExport2.module.bean.ExportCellDesc;
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
 * 解析导出Excel的XML描述文件
 */
public class ParseXMLUtil {
    private ParseXMLUtil() {
    }

    /**
     * 根据给定的XML文件解析出Excel的结构
     * @param xmlFile xml文件路径
     * @return
     */
    public static ExcelExportStruct parseExportStruct(String xmlFile) {
        if (StringUtils.isEmpty(xmlFile)) {
            return null;
        }
        InputStream is = null;
        ExcelExportStruct excelStruct = null;
        SAXBuilder saxBuilder = null;
        Document document = null;
        try {
            is = new FileInputStream(xmlFile);
            if (is == null) {
                throw new FileNotFoundException("Excel的描述文件 : " + xmlFile + " 未找到.");
            }
            saxBuilder = new SAXBuilder(false);
            document = saxBuilder.build(is);
            // 根节点
            Element root = document.getRootElement();
            // 一次导出
            List onceList = root.getChildren("onceExport");
            // 重复导出
            List repeatList = root.getChildren("repeatExport");
            excelStruct = new ExcelExportStruct();
            // 读取每个sheet页最大导出数据量 （默认4000）
            int exportNumber = Integer.parseInt((root.getChild("export-number")).getTextTrim());
            excelStruct.setExportNumber(exportNumber);

            parseOnceExport(excelStruct, onceList);
            parseRepeatExport(excelStruct, repeatList);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ExcelGetConfigException("获取最大导出数据量（配置为：export-number）出现错误，只能设置为正整数。");
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
     * 解析离散数据导出CDATA区
     * @param excelStruct Excel导出描述文件的结构对象
     * @param onceList 离散数据导出配置信息
     */
    private static void parseOnceExport(ExcelExportStruct excelStruct, List onceList) {
        if (onceList == null || onceList.size() <= 0) {
            return;
        }
        Element onceElem = (Element) onceList.get(0);
        List cellDescList = onceElem.getChildren("cellDesc");
        if (cellDescList == null || cellDescList.size() <= 0) {
            return;
        }
        List<ExportCellDesc> onceExportCells = new ArrayList<ExportCellDesc>();
        // 遍历每一个单元格的描述信息
        getExportCellDesc(excelStruct, cellDescList, onceExportCells);
        excelStruct.setOnceExportCells(onceExportCells);
    }

    /**
     * 解析列表数据导出CDATA区
     * @param excelStruct Excel导出描述文件的结构对象
     * @param repeatList 列表数据导出配置信息
     */
    private static void parseRepeatExport(ExcelExportStruct excelStruct, List repeatList) {
        Element onceElem = (Element) repeatList.get(0);
        List cellDescList = onceElem.getChildren("cellDesc");
        if (cellDescList == null || cellDescList.size() <= 0) {
            return;
        }
        // 读取开始、终止行
        int startLine = 1;
        try {
            startLine = Integer.parseInt(((Element) onceElem.getChild("start-line")).getTextTrim());
        } catch (IndexOutOfBoundsException e) {
            throw new ExcelExportException("导出Excel失败 : 请在XML描述文件中添加<start-line/>配置项!");
        }
        excelStruct.setStartLine(startLine);
        // 遍历每一个单元格的描述信息
        List<ExportCellDesc> repeatExportCells = new ArrayList<ExportCellDesc>();
        getExportCellDesc(excelStruct, cellDescList, repeatExportCells);
        if (repeatExportCells == null || repeatExportCells.size() <= 0) {
            return;
        }
        excelStruct.setRepeatExportCells(repeatExportCells);
    }

    /**
     * 获取含有每个单元格信息、校验信息的集合list
     * @param excelStruct Excel导入描述文件的结构对象
     * @param cellDescList 单元格描述信息集合
     * @param list
     */
    private static void getExportCellDesc(ExcelExportStruct excelStruct, List cellDescList, List<ExportCellDesc> list){
        // 遍历每一个单元格的描述信息
        ExportCellDesc cellDesc = null;
        for (Object obj : cellDescList) {
            if (obj == null) {
                continue;
            }
            Element cellElem = (Element) obj;
            String cellRef = ((Element) cellElem.getChild("cellRef")).getTextTrim();
            String fieldName = ((Element) cellElem.getChild("fieldName")).getTextTrim();
            cellDesc = new ExportCellDesc();
            cellDesc.setCellRef(cellRef.toUpperCase());
            cellDesc.setFieldName(fieldName);
            list.add(cellDesc);
        }
    }
}