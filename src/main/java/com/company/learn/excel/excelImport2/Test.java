package com.company.learn.excel.excelImport2;

import com.company.learn.excel.excelImport2.bean.ExcelData;
import com.company.learn.excel.excelImport2.bean.ExcelStruct;
import com.company.learn.excel.excelImport2.userinterface.ExcelImportUtil;
import com.company.learn.excel.excelImport2.util.ParseXMLUtil;


public class Test {
    public static void main(String[] args) throws Exception {
        // 读取excel导入的数据
        ExcelData data = testExcelImp();

//        // 1. 解析XML描述文件
//        String xmlFile = "E:\\workspace-idea\\SpringMVC\\SpringMVCModule\\" +
//                "src\\main\\java\\com\\company\\learn\\excel\\excelImport2\\xml\\excel_desc.xml";
//        ExcelStruct excelStruct = ParseXMLUtil.parseImportStruct(xmlFile);
//        System.out.println(excelStruct);


    }

    /**
     * 测试excel导入
     */
    private static ExcelData testExcelImp() throws Exception {
        String xmlFile = "E:/workspace-idea/SpringMVC/SpringMVCModule/src/" +
                "main/java/com/company/learn/excel/excelImport2/xml/excel_desc.xml";
        String importFile = "C:/Users/dell/Desktop/test.xlsx";
        ExcelData data = ExcelImportUtil.readExcel(xmlFile, importFile);
        System.out.println(data);
        return data;
    }

}