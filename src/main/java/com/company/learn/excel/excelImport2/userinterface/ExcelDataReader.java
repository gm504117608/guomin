package com.company.learn.excel.excelImport2.userinterface;

import com.company.learn.excel.POIExcelUtil;
import com.company.learn.excel.excelImport2.bean.ExcelData;
import com.company.learn.excel.excelImport2.bean.ExcelStruct;
import com.company.learn.excel.excelImport2.bean.ImportCellDesc;
import com.company.learn.excel.excelImport2.exception.ExcelGetConfigException;
import com.company.learn.excel.excelImport2.util.ValidateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据描述文件，读取Excel的内容
 */
public class ExcelDataReader {

    private ExcelDataReader() {
    }

    /**
     * 读取并组装Excel中的信息
     *
     * @param excelStruct Excel结构描述文件
     * @param importFile  被导入的excel文件
     * @param sheetIndex  读取的sheetIndex，从0开始
     * @return 组装后的Excel数据
     */
    public static ExcelData readExcel(ExcelStruct excelStruct, String importFile, int sheetIndex) throws Exception {
        if (null == excelStruct) {
            throw new ExcelGetConfigException("excel导入xml配置文件获取不到");
        }
        // 读取excel
        Workbook wb = POIExcelUtil.getExcelWorkbook(importFile);
        Sheet sheet = wb.getSheetAt(sheetIndex);
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

        List<ImportCellDesc> repeatImportCells = excelStruct.getRepeatImportCells();
        List<ImportCellDesc> onceImportCells = excelStruct.getOnceImportCells();
        ExcelData excelData = new ExcelData();
        // 如果有离散数据描述，则获取离散数据
        if (null != onceImportCells && repeatImportCells.size() > 0) {
            readOnceImportData(onceImportCells, sheet, evaluator, excelData);
        }
        int repeatCount;
        // 如果有列表数据描述，则获取列表数据
        if(null != repeatImportCells && repeatImportCells.size() > 0){
            int startLine = excelStruct.getStartLine();
            int endLine = excelStruct.getEndLine();
            // 读取所有的重复数据 并返回读取条数
            repeatCount = readRepeatImportData(repeatImportCells, sheet, evaluator, startLine, endLine, excelData);
        }
        // 进行数据校验
        if (excelStruct.isRuleCheck()) {
            ValidateUtil.processValidate(excelData);
        }
        return excelData;
    }

    /**
     * 读取导入的离散数据
     *
     * @param onceImportCells 离散数据描述
     * @param sheet           处理sheet页对象
     * @param evaluator       获取单元格信息的公式对象
     * @param excelData       需要组装的数据
     */
    private static void readOnceImportData(List<ImportCellDesc> onceImportCells, Sheet sheet,
                                           FormulaEvaluator evaluator, ExcelData excelData) {
        if (null == onceImportCells || onceImportCells.size() <= 0 ||
                null == sheet || sheet.getLastRowNum() <= 0) {
            return;
        }
        Map<String, ImportCellDesc> onceData = new HashMap<String, ImportCellDesc>();
        CellReference cellReference = null;
        Row row = null;
        Cell cell = null;
        for (ImportCellDesc desc : onceImportCells) {
            if (null == desc || StringUtils.isEmpty(desc.getCellRef())) {
                continue;
            }
            // 获取单元格
            cellReference = new CellReference(desc.getCellRef());
            row = sheet.getRow(cellReference.getRow());
            cell = row.getCell(cellReference.getCol());
            // 获取值
            desc.setFieldValue(getCellValue(evaluator, cell));
            onceData.put(desc.getFieldName(), desc);
        }
        excelData.setOnceData(onceData);
    }

    /**
     * 读取导入的列表数据
     *
     * @param repeatImportCells 列表数据描述
     * @param sheet             处理sheet页对象
     * @param evaluator         获取单元格信息的公式对象
     * @param startLine         重复数据结束标识
     * @param excelData         需要组装的数据
     * @return 重复数据读取条数
     */
    private static int readRepeatImportData(List<ImportCellDesc> repeatImportCells, Sheet sheet, FormulaEvaluator evaluator,
                                            int startLine, int endLine, ExcelData excelData) {
        int repeatCount = 0;
        if (null == repeatImportCells || repeatImportCells.size() <= 0 ||
                null == sheet || sheet.getLastRowNum() <= 0) {
            return repeatCount;
        }
        List<Map<String, ImportCellDesc>> repeatData = new ArrayList<Map<String, ImportCellDesc>>();
        Map<String, ImportCellDesc> map = null;
        ImportCellDesc newDesc = null;
        CellReference ref = null;
        Row row = null;
        Cell cell = null;
        for (int i = startLine; i <= endLine; i++) {
            map = new HashMap<String, ImportCellDesc>();
            row = sheet.getRow(i);
            if (null == row) {
                continue;
            }
            for (ImportCellDesc desc : repeatImportCells) {
                // 一定要使用新的desc，否则会有问题。
                newDesc = new ImportCellDesc(desc);
                ref = new CellReference(i, newDesc.getCellCol() - 1);
                newDesc.setCellRef(ref.formatAsString());    // 重新设置位置
                map.put(newDesc.getFieldName(), newDesc);
                cell = row.getCell(newDesc.getCellCol() - 1);
                if (null == cell) {
                    continue;
                }
                // 获取值
                newDesc.setFieldValue(getCellValue(evaluator, cell));
            }
            repeatCount++;        // 成功添加一行记录
            repeatData.add(map);
        }
        excelData.setRepeatData(repeatData);
        return repeatCount;
    }

    /**
     * 获取单元格的值
     */
    private static String getCellValue(FormulaEvaluator evaluator, Cell cell) {
        String fieldValue = "";
        if (cell != null) {
            switch (evaluator.evaluateInCell(cell).getCellType()) {
                case Cell.CELL_TYPE_STRING:        // 字符串
                    fieldValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:    // bool型
                    fieldValue = cell.getBooleanCellValue() + "";
                    break;
                case Cell.CELL_TYPE_NUMERIC:    // 数值型
                    fieldValue = Double.toString(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:        // 空
                    fieldValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    fieldValue = cell.getErrorCellValue() + "";
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    fieldValue = "";
                    break;
            }
        }
        return fieldValue;
    }
}