package com.company.learn.excel.excelImport2.util;

import com.company.learn.excel.excelImport2.bean.ExcelData;
import com.company.learn.excel.excelImport2.bean.ImportCellDesc;
import com.company.learn.excel.excelImport2.bean.validate.Validation;
import com.company.learn.excel.excelImport2.validate.AbstractValidator;
import com.company.learn.excel.excelImport2.validate.ContainValueValidator;
import com.company.learn.excel.excelImport2.validate.DateFormatValidator;
import com.company.learn.excel.excelImport2.validate.LengthValidator;
import org.apache.commons.lang3.StringUtils;

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
     * 校验导入数据格式正确性入口
     *
     * @param excelData 需要组装的数据
     */
    public static void processValidate(ExcelData excelData) {
        if (null == excelData) {
            return;
        }
        validateOnceData(excelData);
        validateRepeatData(excelData);
    }

    /**
     * 校验离散数据
     *
     * @param excelData 需要组装的数据
     */
    private static void validateOnceData(ExcelData excelData) {
        if (null == excelData) {
            return;
        }
        // 离散数据
        Map<String, ImportCellDesc> onceData = excelData.getOnceData();
        if (null == onceData || onceData.size() <= 0) {
            return;
        }
        validateCell(excelData, onceData);
    }

    /**
     * 校验列表数据
     *
     * @param excelData 需要组装的数据
     */
    private static void validateRepeatData(ExcelData excelData) {
        if (null == excelData) {
            return;
        }
        // 列表数据
        List<Map<String, ImportCellDesc>> repeatData = excelData.getRepeatData();
        if (null == repeatData || repeatData.size() <= 0) {
            return;
        }
        for (Map<String, ImportCellDesc> map : repeatData) {
            if (null == map || map.size() <= 0) {
                continue;
            }
            validateCell(excelData, map);
        }
    }

    /**
     *
     * @param excelData 需要组装的数据
     * @param data 单元格描述map对象
     */
    private static void validateCell(ExcelData excelData, Map<String, ImportCellDesc> data){
        Set<String> keys = data.keySet();
        for (String key : keys) {
            if (StringUtils.isEmpty(key)) {
                continue;
            }
            ImportCellDesc cellDesc = data.get(key);
            // 执行单元格数据校验
            validateCell(excelData, cellDesc);
        }
    }

    /**
     * 处理单元格的数据校验工作
     *
     * @param excelData 需要组装的数据
     * @param cellDesc  单元格描述
     */
    private static void validateCell(ExcelData excelData, ImportCellDesc cellDesc) {
        // 如果没有设置校验器
        if (null == excelData || null == cellDesc || null == cellDesc.getValidationList()) {
            return;
        }
        List<Validation> validators = cellDesc.getValidationList();
        String validator = null;
        try {
            for (Validation v : validators) {
                validator = v.getName();
                Class<?> clazz = Class.forName(validator);
                Object obj = clazz.newInstance();
                if (null == obj) {
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
                // 执行校验
                String errorMsg = null;
                if ("com.company.learn.excel.excelImport2.validate.LengthValidator".equals(validator)) {
                    LengthValidator l = (LengthValidator) validatorInstance;
                    l.setMinLength(v.getMinLength());
                    l.setMaxLength(v.getMaxLength());
                    errorMsg = l.processValidate();
                } else if ("com.company.learn.excel.excelImport2.validate.DateFormatValidator".equals(validator)) {
                    DateFormatValidator d = (DateFormatValidator) validatorInstance;
                    d.setDateFormat(v.getDateFormat());
                    errorMsg = d.processValidate();
                } else if ("com.company.learn.excel.excelImport2.validate.ContainValueValidator".equals(validator)) {
                    ContainValueValidator cv = (ContainValueValidator) validatorInstance;
                    cv.setContainStr(v.getContainStr());
                    errorMsg = cv.processValidate();
                } else {
                    errorMsg = validatorInstance.processValidate();
                }
                // 如果校验
                if (StringUtils.isNotEmpty(errorMsg) || !AbstractValidator.OK.equals(errorMsg)) {
                    excelData.addErrorMsg(cellDesc.getCellRef(), errorMsg);
                }
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