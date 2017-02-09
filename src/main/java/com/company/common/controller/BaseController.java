package com.company.common.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前端控制层的父类，做一些公用的处理
 */
public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ModelAndView modelAndView;

    /**
     * @ModelAttribute 注释的方法会在此controller每个方法执行前被执行
     * @param request
     * @param response
     * @throws Exception
     */
    @ModelAttribute
    public void setParams(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        this.response = response;
        this.modelAndView = new ModelAndView();
    }

    /**
     * 写入需要返回界面地址
     * @param viewName
     */
    public void setViewName(String viewName){
        this.modelAndView.setViewName("forward:" + viewName);
    }

    /**
     * 增加参数到返回对象modelAndView中
     * @param objName 对象名称
     * @param obj 对象值
     */
    public void addViewObj(String objName, Object obj){
        this.modelAndView.addObject(objName, obj);
    }

    /**
     * 界面跳转 使用的是redirect界面重定向
     * @param path
     * @return
     */
    public void redirect(String path){
        this.modelAndView.setViewName("redirect:" + path);
    }

}
