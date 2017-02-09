package com.company.learn.excel.excelImport2.userinterface;

import com.company.learn.excel.excelImport2.bean.ExcelData;
import com.company.learn.excel.excelImport2.bean.ExcelStruct;
import com.company.learn.excel.excelImport2.exception.ExcelGetConfigException;
import com.company.learn.excel.excelImport2.exception.ExcelImportException;
import com.company.learn.excel.excelImport2.util.ParseXMLUtil;
import org.apache.commons.lang3.StringUtils;
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
     * 目前只支持一个sheet页面的文件导入
     *
     * @param xmlFile  描述被导入的Excel的格式的XML文件
     * @param importFile 被导入的excel文件
     * @return Excel中需要导入的数据
     */
    public static ExcelData readExcel(String xmlFile, String importFile) throws ExcelImportException {
        if (StringUtils.isEmpty(xmlFile) || StringUtils.isEmpty(importFile)) {
            return null;
        }
        try {
            // 解析XML描述文件
            ExcelStruct excelStruct = ParseXMLUtil.parseImportStruct(xmlFile);
            // 按照XML描述文件，来解析Excel中文件的内容
            return ExcelDataReader.readExcel(excelStruct, importFile, 0);
        } catch (FileNotFoundException e) {
            // log.error("导入Excel失败 - XML描述文件未找到 : ", e);
            throw new ExcelImportException("导入Excel失败 - XML描述文件未找到 : ", e);
        } catch (JDOMException e) {
            // log.error("导入Excel失败 - 解析XML描述文件异常 : ", e);
            throw new ExcelImportException("导入Excel失败 - 解析XML描述文件异常 : ", e);
        } catch (IOException e) {
            // log.error("导入Excel失败 - IO异常 : ", e);
            throw new ExcelImportException("导入Excel失败 - IO异常 : ", e);
        } catch (ExcelGetConfigException e){
//            log.error("导入Excel失败- 解析XML描述文件错误 : ", e);
            throw new ExcelImportException("导入Excel失败- 解析XML描述文件错误 : ", e);
        } catch (Exception e) {
            // log.error("导入Excel失败 : ", e);
            throw new ExcelImportException("导入Excel失败 : ", e);
        }
    }
}