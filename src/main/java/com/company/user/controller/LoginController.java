package com.company.user.controller;

import com.company.common.ResultData;
import com.company.common.controller.BaseController;
import com.company.user.model.SysUser;
import com.company.user.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 登录controller
 */
@Controller
public class LoginController extends BaseController {

    private static Logger log = Logger.getLogger(LoginController.class);

    @Resource
    private LoginService loginService;

    /**
     * 跳转到登录界面
     * @return
     */
    @RequestMapping(value = "/toLogin.html")
    public ModelAndView toLogin(){
        log.info("登录界面跳转");

        setViewName("/WEB-INF/page/login.jsp");
        return this.modelAndView;
    }

    /**
     * 登录系统校验
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/login.html", method={RequestMethod.POST})
    @ResponseBody
    public ResultData login(SysUser sysUser){
        log.info("登录用户sysUser" + sysUser.toString());

        ResultData rd = new ResultData();
        if(null == sysUser){
            rd.setCode("-1");
            rd.setMessage("请输入登录信息！");
            return rd;
        }
        rd = loginService.login(request, response, sysUser);
        return rd;
    }

}
