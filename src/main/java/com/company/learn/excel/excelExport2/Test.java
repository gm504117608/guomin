package com.company.learn.excel.excelExport2;

import com.company.learn.excel.excelExport2.userinterface.ExcelExportUtil;
import com.company.learn.excel.excelImport2.bean.ExcelData;
import com.company.learn.excel.excelImport2.bean.ImportCellDesc;
import com.company.learn.excel.excelImport2.userinterface.ExcelImportUtil;
import com.company.learn.excel.excelExport2.module.ExcelModule;
import com.company.learn.excel.excelExport2.module.SheetModule;
import com.company.learn.excel.excelExport2.module.bean.ExcelExportStruct;
import com.company.learn.excel.excelExport2.util.ParseXMLUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


public class Test {
    public static void main(String[] args) throws Exception {

        // 读取excel导入的数据
        ExcelData data = testExcelImp();
        testExcelExp(data);


        // 1. 解析XML描述文件
//        String xmlFile = "E:\\workspace-idea\\SpringMVC\\SpringMVCModule\\src\\main\\java\\com\\company\\learn\\excel\\excelExport2\\xml\\excel_desc.xml";
//        ExcelExportStruct excelStruct = ParseXMLUtil.parseExportStruct(xmlFile);
//        System.out.println(excelStruct);


    }

    /**
     * 测试excel导出
     */
    private static void testExcelExp(ExcelData data) throws Exception {

        String xmlFile = "E:\\workspace-idea\\SpringMVC\\SpringMVCModule\\src\\main\\java\\com\\company\\" +
                "learn\\excel\\excelExport2\\xml\\excel_desc.xml";
        ExcelExportStruct excelStruct = ParseXMLUtil.parseExportStruct(xmlFile);
        System.out.println(excelStruct);

        SheetModule sheetModule = new SheetModule(changeExcelDataToOnce(data), changeExcelDataToRepeat(data));
        ExcelModule excelModule = new ExcelModule(sheetModule);
        // 导出excel时使用的模板：exp_templete.xls
//        String templateFile = new File(System.getProperty("user.dir"), "exp_templete.xls").getAbsolutePath();
        String templateFile = "C:\\Users\\dell\\Desktop\\exp_template.xls";
        // 导出的文件名：exp_out.xls
        ExcelExportUtil.exportExcel(excelStruct, excelModule, templateFile);
    }

    /**
     * 测试excel导入
     */
    private static ExcelData testExcelImp() throws Exception {
        String xmlFile = "E:\\workspace-idea\\SpringMVC\\SpringMVCModule\\src\\main\\" +
                "java\\com\\company\\learn\\excel\\excelImport2\\xml\\excel_desc.xml";
        String importFile = "C:/Users/dell/Desktop/test.xlsx";
        ExcelData data = ExcelImportUtil.readExcel(xmlFile, importFile);
        return data;
    }

    /**
     * 将ExcelData转化为SimpleExcelData
     *
     * @param excelData excel导入数据封装对象
     * @return 返回SimpleExcelData数据对象
     */
    public static Map<String, String> changeExcelDataToOnce(ExcelData excelData) {
        if (excelData == null) {
            return null;
        }
        Map<String, ImportCellDesc> onceData = excelData.getOnceData();
        Map<String, String> simpleOnceData = null;
        if (onceData != null && onceData.size() > 0) {
            simpleOnceData = new HashMap<String, String>();
            Set<String> keys = onceData.keySet();
            for (String key : keys) {
                if (StringUtils.isEmpty(key) || onceData.get(key) == null)
                    continue;
                simpleOnceData.put(key, onceData.get(key).getFieldValue());
            }
        }
        return simpleOnceData;
    }

    /**
     * 将ExcelData转化为SimpleExcelData
     *
     * @param excelData excel导入数据封装对象
     * @return 返回SimpleExcelData数据对象
     */
    public static List<Map<String, String>> changeExcelDataToRepeat(ExcelData excelData) {
        if (excelData == null) {
            return null;
        }
        List<Map<String, ImportCellDesc>> repeatData = excelData.getRepeatData();
        List<Map<String, String>> simpleRepeatData = null;
        if (repeatData != null && repeatData.size() > 0) {
            simpleRepeatData = new ArrayList<Map<String, String>>();
            for (Map<String, ImportCellDesc> map : repeatData) {
                if (map == null)
                    continue;
                Map<String, String> tmp = new HashMap<String, String>();
                Set<String> keys = map.keySet();
                for (String key : keys) {
                    if (StringUtils.isEmpty(key) || map.get(key) == null)
                        continue;
                    tmp.put(key, map.get(key).getFieldValue());
                }
                simpleRepeatData.add(tmp);
            }
        }
        return simpleRepeatData;
    }

}