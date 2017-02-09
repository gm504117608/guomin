package com.company.user.controller;

import com.company.common.ResultData;
import com.company.common.controller.BaseController;
import com.company.user.model.SysUser;
import com.company.user.service.ForgetService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 忘记密码处理controller
 */
@Controller
public class ForgetController extends BaseController {

    private static Logger log = Logger.getLogger(ForgetController.class);

    @Resource
    private ForgetService forgetService;

    /**
     * 忘记密码
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/forget.html", method={RequestMethod.POST})
    @ResponseBody
    public ResultData forget(SysUser sysUser) {
        log.info("忘记密码sysUser" + sysUser.toString());

        ResultData rd = new ResultData();
        if(null == sysUser){
            rd.setCode("-1");
            rd.setMessage("请输入修改密码信息！");
            return rd;
        }

        rd = forgetService.forget(request, sysUser);
        return rd;
    }
}
