package com.company.learn.generate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateModel {

    /**
     * @param ftlPath    模板所在路径
     * @param ftlName    模板名
     * @param fileName   model类的名字
     * @param filePath   model层的路径
     * @param packageName model层的包名
     * @param list       实体类字段
     */
    public static void Generate(String ftlPath, String ftlName, String fileName, String filePath, String packageName, List<Object> list) throws IOException, TemplateException {
        PathUtil.Path_Judge_Exist(ftlPath);
        PathUtil.Path_Judge_Exist(filePath);

        //实体类需要其他参数
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("packageName", packageName);
        root.put("className", fileName);
        root.put("attrs", list);

        Configuration cfg = new Configuration();
        String path = System.getProperty("user.dir") + ftlPath;

        cfg.setDirectoryForTemplateLoading(new File(path));
        Template template = cfg.getTemplate(ftlName);

        PathUtil.printFile(root, template, filePath, fileName);
    }

}
