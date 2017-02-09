package com.company.common.config;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.Serializable;
import java.util.Properties;

/**
 * 系统使用的一些公共配置文件，在启动项目的时候加载配置文件（sysconfig.properties）信息到Environment类中
 * 提供整个项目使用
 */
public class Environment extends PropertyPlaceholderConfigurer implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(Environment.class);

    /**
     * 模板文件的存放位置
     */
    public static String TEMPLATE_LOCATION;
    /**
     * 模板xml描述文件的存放位置
     */
    public static String TEMPLATE_DESCRIPTION_LOCATION;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                    Properties props) {
        try{
            super.processProperties(beanFactoryToProcess, props);
            LOGGER.info("加载sysconfig.properties");
            TEMPLATE_LOCATION = props.getProperty("template.location");
            TEMPLATE_DESCRIPTION_LOCATION = props.getProperty("template.description.location");
        }catch(BeansException e){
            e.printStackTrace();
            LOGGER.error("加载sysconfig.properties失败: " + e.getMessage());
        }
    }

    public Environment() {}
}


