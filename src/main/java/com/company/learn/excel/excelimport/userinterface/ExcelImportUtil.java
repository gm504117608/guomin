package com.company.learn.excel.excelimport.userinterface;

import com.company.learn.excel.excelimport.bean.ExcelData;
import com.company.learn.excel.excelimport.bean.ExcelStruct;
import com.company.learn.excel.excelimport.bean.SimpleExcelData;
import com.company.learn.excel.excelimport.exception.ExcelImportException;
import com.company.learn.excel.excelimport.util.ExcelDataReader;
import com.company.learn.excel.excelimport.util.ExcelDataUtil;
import com.company.learn.excel.excelimport.util.ParseXMLUtil;
import com.company.learn.excel.excelimport.util.StringUtil;
import org.jdom.JDOMException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 读取导入的Excel的内容
 * 模板要求：
 * （1）开始重复行与End行 有且只能有 一空行
 */
public class ExcelImportUtil {
    private ExcelImportUtil() {
    }

    // private static Logger log = Logger.getLogger(ExcelImportUtil.class);

    /**
     * 读取导入的Excel的文件内容
     *
     * @param xmlFile  描述被导入的Excel的格式的XML文件
     * @param importFile 被导入的excel文件
     * @return Excel中需要导入的数据
     */
    public static ExcelData readExcel(String xmlFile, String importFile) throws ExcelImportException {
        if (StringUtil.isEmpty(xmlFile) || StringUtil.isEmpty(importFile)) {
            return null;
        }
        try {
            // 1. 解析XML描述文件
            ExcelStruct excelStruct = ParseXMLUtil.parseImportStruct(xmlFile);
            // 2. 按照XML描述文件，来解析Excel中文件的内容
            return ExcelDataReader.readExcel(excelStruct, importFile, 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // log.error("导入Excel失败 - XML描述文件未找到 : ", e);
            throw new ExcelImportException("导入Excel失败 - XML描述文件未找到 : ", e);
        } catch (JDOMException e) {
            e.printStackTrace();
            // log.error("导入Excel失败 - 解析XML描述文件异常 : ", e);
            throw new ExcelImportException("导入Excel失败 - 解析XML描述文件异常 : ", e);
        } catch (IOException e) {
            e.printStackTrace();
            // log.error("导入Excel失败 - IO异常 : ", e);
            throw new ExcelImportException("导入Excel失败 - IO异常 : ", e);
        } catch (Exception e) {
            e.printStackTrace();
            // log.error("导入Excel失败 : ", e);
            throw new ExcelImportException("导入Excel失败 : ", e);
        }
    }

    ;

    /**
     * 读取导入的Excel的文件内容
     *
     * @param xmlFile           描述被导入的Excel的格式的XML文件
     * @param importFile 被导入的excel文件
     * @return Excel中需要导入的数据
     */
    public static SimpleExcelData simpleReadExcel(String xmlFile, String importFile) throws ExcelImportException {
        ExcelData excelData = readExcel(xmlFile, importFile);
        return ExcelDataUtil.changeExcelDataToSimple(excelData);
    }
}