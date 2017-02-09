package com.company.learn.excel.excelimport.util;

import com.company.learn.excel.excelimport.bean.ExcelData;
import com.company.learn.excel.excelimport.bean.ImportCellDesc;
import com.company.learn.excel.excelimport.validate.AbstractValidator;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 处理Excel数据校验
 */
public class ValidateUtil {
    private ValidateUtil() {
    }

    /**
     * 校验导入元素格式入口
     * @param excelData 需要组装的数据
     */
    public static void processValidate(ExcelData excelData) {
        if (excelData == null) {
            return;
        }
        // 校验一次导入数据
        validateOnceData(excelData);
        // 校验多次导入的数据
        validateRepeatData(excelData);
    }

    /**
     * 校验一次导入数据
     * @param excelData 需要组装的数据
     */
    private static void validateRepeatData(ExcelData excelData) {
        // 多次导入的数据
        List<Map<String, ImportCellDesc>> repeatData = excelData.getRepeatData();
        if (repeatData == null || repeatData.size() <= 0) {
            return;
        }
        for (Map<String, ImportCellDesc> map : repeatData) {
            if (map == null || map.size() <= 0) {
                continue;
            }
            Set<String> keys = map.keySet();
            for (String key : keys) {
                if (StringUtil.isEmpty(key)) {
                    continue;
                }
                ImportCellDesc cellDesc = map.get(key);
                // 执行单元格数据校验
                validateCell(excelData, cellDesc);
            }
        }
    }

    /**
     * 校验多次导入的数据
     * @param excelData 需要组装的数据
     */
    private static void validateOnceData(ExcelData excelData) {
        // 一次导入的数据
        Map<String, ImportCellDesc> onceData = excelData.getOnceData();
        if (onceData == null || onceData.size() <= 0) {
            return;
        }
        Set<String> keys = onceData.keySet();
        for (String key : keys) {
            if (StringUtil.isEmpty(key)) {
                continue;
            }
            ImportCellDesc cellDesc = onceData.get(key);
            // 执行单元格数据校验
            validateCell(excelData, cellDesc);
        }
    }

    /**
     * 处理单元格的数据校验工作
     * @param excelData 需要组装的数据
     * @param cellDesc 单元格描述
     */
    private static void validateCell(ExcelData excelData, ImportCellDesc cellDesc) {
        // 如果没有设置校验器
        if (excelData == null || cellDesc == null || cellDesc.getValidatorList() == null || cellDesc.getValidatorList().size() <= 0) {
            return;
        }
        List<Map<String, String>> validators = cellDesc.getValidatorList();
        String validator = null;
        for (Map<String, String> map : validators) {
            try {
                validator = map.get("validator");
                Class<?> clazz = Class.forName(validator);
                Object obj = clazz.newInstance();
                if (obj == null) {
                    continue;
                }
                if (!(obj instanceof AbstractValidator)) {
                    excelData.addErrorMsg(cellDesc.getCellRef(), "请检查, 数据校验器必须继承AbstractValidator : " + validator);
                    continue;
                }
                AbstractValidator validatorInstance = (AbstractValidator) obj;
                // 设置单元格的值、位置
                validatorInstance.setCellRef(cellDesc.getCellRef());
                validatorInstance.setFieldValue(cellDesc.getFieldValue());
                validatorInstance.setCell(cellDesc);
                validatorInstance.setOnceCellList(excelData.getOnceCellList());
                validatorInstance.setRepeatCellList(excelData.getRepeatCellList());
                validatorInstance.setAllCellList(excelData.getAllCellList());
                // 执行校验
                String errorMsg = null;
                if("com.company.learn.excel.excelimport.validate.LengthValidator".equals(validator)){
                    String maxLength = map.get("maxLength");
                    String minLength = map.get("minLength");
                    validatorInstance.setMinLength(minLength);
                    validatorInstance.setMaxLength(maxLength);
                    errorMsg = validatorInstance.processValidate();
                }else{
                    errorMsg = validatorInstance.processValidate();
                }
                // 如果校验
                if (StringUtil.isNotEmpty(errorMsg) || !AbstractValidator.OK.equals(errorMsg)) {
                    excelData.addErrorMsg(cellDesc.getCellRef(), errorMsg);
                }

            } catch (NumberFormatException e) {
                excelData.addErrorMsg(AbstractValidator.SYSTEM_ERROR, "请检查, 长度数据校验器的长度大小值没有设置 : " + validator);
                // e.printStackTrace();
            } catch (ClassNotFoundException e) {
                excelData.addErrorMsg(AbstractValidator.SYSTEM_ERROR, "请检查, 不存在的数据校验器 : " + validator);
                // e.printStackTrace();
            } catch (InstantiationException e) {
                excelData.addErrorMsg(AbstractValidator.SYSTEM_ERROR, "请检查, " + validator + ", 必须要有空构造函数!");
                // e.printStackTrace();
            } catch (IllegalAccessException e) {
                excelData.addErrorMsg(AbstractValidator.SYSTEM_ERROR, "请检查, " + validator + ", 构造函数必须为public!");
                // e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}