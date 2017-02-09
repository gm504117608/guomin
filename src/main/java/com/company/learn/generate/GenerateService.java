package com.company.learn.generate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenerateService {

    public static void Generate(String ftlPath, String ftlName,
                                String fileName, String filePath, String packageName,
                                String daopackageName, String modelpackageName) throws IOException, TemplateException {

        String ImplFilePath = filePath + "\\impl";
        PathUtil.Path_Judge_Exist(ftlPath);
        PathUtil.Path_Judge_Exist(filePath);
        PathUtil.Path_Judge_Exist(ImplFilePath);

        //实体类需要其他参数
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("modelpackageName", modelpackageName);
        root.put("daopackageName", daopackageName);
        root.put("packageName", packageName);
        root.put("className", fileName);
        root.put("implflag", false);
        Configuration cfg = new Configuration();
        String path = System.getProperty("user.dir") + ftlPath;

        cfg.setDirectoryForTemplateLoading(new File(path));
        Template template = cfg.getTemplate(ftlName);

        PathUtil.printFile(root, template, filePath, fileName + "Service");

        //生成Impl文件
        root.put("ImplpackageName", packageName + "." + fileName + "Service");
        root.put("packageName", packageName + ".impl");
        root.put("implflag", true);
        PathUtil.printFile(root, template, ImplFilePath, fileName + "ServiceImpl");

    }
}
