package com.company.learn.excel.excelExport2.userinterface;

import com.company.learn.excel.POIExcelUtil;
import com.company.learn.excel.excelImport2.exception.ExcelGetConfigException;
import com.company.learn.excel.excelExport2.module.ExcelModule;
import com.company.learn.excel.excelExport2.module.bean.ExcelExportStruct;
import com.company.learn.excel.excelExport2.module.bean.ExportCellDesc;
import com.company.learn.excel.excelExport2.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Excel导出工具类,使用POI的API操作Excel
 */
public class ExcelExportUtil {

    private static CellStyle cellStyle; // 自动换行的样式

    private ExcelExportUtil() {
    }

    /**
     * 导出Excel
     * 通常情况下，不需要知道最终生成的临时文件存放在哪里，WEB中生成完后输出到客户端后会删除，所以，一般不需要传入这个参数
     *
     * @param excelStruct  excel描述xml配置
     * @param excelModule  输出时所需要的数据
     * @param templateFile 模板文件名（带路径）
     * @return 所导出的文件
     */
    public static File exportExcel(ExcelExportStruct excelStruct, ExcelModule excelModule,
                                String templateFile) throws Exception {
        if (StringUtils.isEmpty(templateFile)) {
            return null;
        }
        File file = new File(templateFile);
        String tmpFile = file.getName();
        return exportExcel(excelStruct, excelModule, templateFile, tmpFile);
    }

    /**
     * 导出Excel
     *
     * @param excelStruct  excel描述xml配置
     * @param excelModule  输出时所需要的数据
     * @param templateFile 模板文件名（带路径）
     * @param tmpFile      导出文件名（带路径）
     * @return 所导出的文件
     */
    public static File exportExcel(ExcelExportStruct excelStruct, ExcelModule excelModule, String templateFile,
                                String tmpFile) throws Exception {
        FileInputStream fis = new FileInputStream(templateFile);
        Workbook wb = POIExcelUtil.getWorkBookByStream(fis);
        cellStyle = wb.createCellStyle();
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            // 如果当前sheet为空,或者行全部 为空,则不用设值了。
            if (sheet == null || sheet.getPhysicalNumberOfRows() == 0) {
                continue;
            }
            // 设置sheet名称
            if (StringUtils.isNotEmpty(excelModule.getSheetName(i))) {
                wb.setSheetName(i, excelModule.getSheetName(i));
            }
            int startLine = excelStruct.getStartLine();
            int exportNumber = excelStruct.getExportNumber();
            List<ExportCellDesc> OnceExportCells = excelStruct.getOnceExportCells();
            List<ExportCellDesc> repeatExportCells = excelStruct.getRepeatExportCells();
            if (null != repeatExportCells || repeatExportCells.size() > 0) {
                int size = repeatExportCells.size();
                if (exportNumber < size) {
                    throw new ExcelGetConfigException("导出的数据量" + size + "超过了设置的数据量" + exportNumber);
                }
            }
            setOnceValue(sheet, i, excelModule, OnceExportCells);
            setMultiValue(sheet, i, excelModule, startLine, repeatExportCells);
        }
        // 生成结果文件
        return createResultFile(wb, fis, tmpFile);
    }

    /**
     * 设置列表数据
     *
     * @param sheet
     * @param sheetIndex
     * @param excelData         导出数据对象
     * @param startLine
     * @param repeatExportCells xml描述的列表信息
     */
    private static void setMultiValue(Sheet sheet, int sheetIndex, ExcelModule excelData,
                                      int startLine, List<ExportCellDesc> repeatExportCells) {
        // 得到要设置的数据信息
        List<Map<String, String>> list = excelData.getMultiData(sheetIndex);
        // 如果没有找到startLine为-1则表示不需要再进行设值了,直接返回
        if (startLine == -1) {
            return;
        }
        // 将数据设置到指定单元格中
        setMultiValue(sheet, list, startLine, repeatExportCells);
    }

    /**
     * 设置列表数据
     *
     * @param sheet
     * @param list              列表数据
     * @param startLine
     * @param repeatExportCells xml描述的列表信息
     */
    private static void setMultiValue(Sheet sheet, List<Map<String, String>> list,
                                      int startLine, List<ExportCellDesc> repeatExportCells) {
        if (list == null || list.size() <= 0 || repeatExportCells == null || repeatExportCells.size() <= 0) {
            return;
        }
        // 2.遍历每一行,设值
        int k = 0;
        Row targetRow = sheet.getRow(startLine); // 获取excel第二行的样式作为导出数据的全部样式（第二行必须有数据，给出样式）
        short height = targetRow.getHeight();
        short endColumn = targetRow.getLastCellNum();
        for (int i = startLine; i <= (startLine + list.size() - 1); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                int lastRowNum = sheet.getLastRowNum();
                if(lastRowNum < i){
                    sheet.shiftRows(i, i+1, 1, true, false);
                }else{
                    sheet.shiftRows(i, lastRowNum, 1, true, false);
                }
                row = sheet.createRow(i);
            }
            row.setHeight(height);
            Map<String, String> data = list.get(k);
            for (short j = 0; j < endColumn; j++) {
                Cell cell = row.createCell(j);
                setAutoLine(cell, targetRow.getCell(j)); // 设置单元格样式
                String cellName = POIExcelUtil.getCellName(i, j);
                String fieldName = ExcelExportStruct.getFieldNameByCellRef(repeatExportCells, cellName.substring(0, 1));
                String value = data.get(fieldName);
                cell.setCellValue(value);
            }
            k++;
        }
    }

    /**
     * 导出离散数据
     *
     * @param sheet
     * @param sheetIndex
     * @param excelData       导出数据对象
     * @param OnceExportCells xml描述的离散信息
     * @throws Exception
     */
    private static void setOnceValue(Sheet sheet, int sheetIndex, ExcelModule excelData,
                                     List<ExportCellDesc> OnceExportCells) throws Exception {
        // 得到要设置的数据信息
        Map<String, String> data = excelData.getSheetOnceData(sheetIndex);
        if (data == null || data.size() <= 0) {
            return;
        }
        if (OnceExportCells == null || OnceExportCells.size() <= 0) {
            return;
        }
        for (ExportCellDesc ecd : OnceExportCells) {
            String cellRef = ecd.getCellRef();
            String fieldName = ecd.getFieldName();
            String value = data.get(fieldName);
            int col = POIExcelUtil.getCellColIndex(cellRef);
            int row = POIExcelUtil.getCellRowIndex(cellRef);
            POIExcelUtil.setCellValue(sheet, row, col, value);
        }
    }

    /**
     * 产生结果文件
     *
     * @param wb
     * @param fis
     * @param tmpFile
     * @return
     * @throws Exception
     */
    private static File createResultFile(Workbook wb, FileInputStream fis, String tmpFile) throws Exception {
        // 防止多个线程同时输出时，文件覆盖。
        String fileName = generateFileName(tmpFile);

        FileOutputStream fos = new FileOutputStream(fileName);
        wb.write(fos);
        fos.close();
        fis.close();
        return new File(fileName);
    }

    /**
     * 生成文件名称
     *
     * @param fileName
     * @return
     */
    private static String generateFileName(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        String dateString = DateUtil.format(new Date());
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return fileName + dateString;
        }
        return fileName.substring(0, index) + dateString + fileName.substring(index);
    }

    /**
     * 设置单元格样式
     *
     * @param cell
     * @param targetCell
     */
    private static void setAutoLine(Cell cell, Cell targetCell) {
        if (null == cell || null == targetCell) {
            return;
        }
        try {
            CellStyle style = targetCell.getCellStyle();
            if (null == style) {
                style = cellStyle;
            }
            style.setWrapText(true);// 设置自动换行
            cell.setCellStyle(style);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}