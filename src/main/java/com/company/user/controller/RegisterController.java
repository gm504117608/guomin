package com.company.user.controller;

import com.company.common.ResultData;
import com.company.common.controller.BaseController;
import com.company.user.model.SysUser;
import com.company.user.service.RegisterService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 注册controller
 */
@Controller
public class RegisterController extends BaseController {

    private static Logger log = Logger.getLogger(RegisterController.class);

    @Resource
    private RegisterService registerService;

    /**
     * 注册新用户
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/register.html", method={RequestMethod.POST})
    @ResponseBody
    public ResultData register(SysUser sysUser){
        log.info("注册用户sysUser" + sysUser.toString());

        ResultData rd = new ResultData();
        if(null == sysUser){
            rd.setCode("-1");
            rd.setMessage("请输入注册信息！");
            return rd;
        }
        rd = registerService.register(request, sysUser);
        return rd;
    }

}
