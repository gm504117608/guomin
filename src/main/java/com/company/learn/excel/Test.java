package com.company.learn.excel;

import com.company.learn.excel.excelexport.module.ExcelModule;
import com.company.learn.excel.excelexport.userinterface.ExcelExpUtil;
import com.company.learn.excel.excelimport.bean.ExcelData;
import com.company.learn.excel.excelimport.bean.SimpleExcelData;
import com.company.learn.excel.excelimport.userinterface.ExcelImportUtil;


public class Test {
    public static void main(String[] args) throws Exception {
        // 读取excel导入的数据
//        ExcelData data = testExcelImp();
        // 将数据通过excel模板输出
        testExcelExp();

        // 1. 解析XML描述文件
//        String xmlFile = "C:/Users/dell/Desktop/excel_desc.xml";
//        ExcelStruct2 excelStruct = ParseXMLUtil2.parseImportStruct(xmlFile);
//        System.out.println(excelStruct);


    }

    /**
     * 测试excel导入
     */
    private static ExcelData testExcelImp() throws Exception {
        String xmlFile = "E:\\workspace-idea\\SpringMVC\\SpringMVCModule\\src\\main\\java\\com\\company\\learn\\excel\\excelimport\\xml\\excel_desc.xml";
        String importFile = "C:/Users/dell/Desktop/test.xlsx";
        ExcelData data = ExcelImportUtil.readExcel(xmlFile, importFile);
        System.out.println(data);
        return data;
    }

    /**
     * 测试excel导出
     */
    private static void testExcelExp() throws Exception {
        String xmlFile = "E:\\workspace-idea\\SpringMVC\\SpringMVCModule\\src\\main\\java\\com\\company\\learn\\excel\\excelimport\\xml\\excel_desc.xml";
        String importFile = "C:/Users/dell/Desktop/test.xlsx";
        SimpleExcelData data = ExcelImportUtil.simpleReadExcel(xmlFile, importFile);
        ExcelModule excelModule = new ExcelModule(data.getRepeatData());
        // 导出excel时使用的模板：exp_templete.xls
//        String templateFile = new File(System.getProperty("user.dir"), "exp_templete.xls").getAbsolutePath();
        String templateFile = "C:\\Users\\dell\\Desktop\\exp_templete.xls";
        // 导出的文件名：exp_out.xls
        ExcelExpUtil.expExcel(excelModule, templateFile);
    }
}