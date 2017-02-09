package com.company.user.service;

import com.company.common.ResultData;
import com.company.common.constant.SysParameter;
import com.company.common.dao.IBaseDao;
import com.company.user.model.SysUser;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 忘记密码处理Service
 */
@Service("forgetService")
public class ForgetService {

    private static Logger log = Logger.getLogger(ForgetService.class);

    @Resource
    private IBaseDao baseDao;

    private static final String nameSpace = "N_SYS_USER.";

    /**
     * 忘记密码
     *
     * @param request
     * @param sysUser
     * @return
     */
    public ResultData forget(HttpServletRequest request, SysUser sysUser) {
        ResultData rd = new ResultData();
        String phoneNum = sysUser.getPhoneNum();
        if(StringUtils.isEmpty(phoneNum)){
            rd.setCode("-1");
            rd.setMessage("电话号码不能为空！");
            return rd;
        }
        String pwd = sysUser.getUserPassword();
        if(StringUtils.isEmpty(pwd)){
            rd.setCode("-1");
            rd.setMessage("登录密码不能为空！");
            return rd;
        }
        String confirmPwd = sysUser.getConfirmPassword();
        if(StringUtils.isEmpty(confirmPwd)){
            rd.setCode("-1");
            rd.setMessage("登录确认密码不能为空！");
            return rd;
        }
        if(!pwd.equals(confirmPwd)){
            rd.setCode("-1");
            rd.setMessage("两次输入的密码不一致！");
            return rd;
        }

        int count = this.baseDao.insert(nameSpace + "updateSysUser", sysUser);
        if(count <=0){
            rd.setCode("-1");
            rd.setMessage("修改密码失败！");
            return rd;
        }
        // 将登录信息存入session中
        HttpSession session = request.getSession(true); // 若存在会话则返回该会话，否则新建一个会话。
        session.setAttribute(SysParameter.LOGIN_SESSION, sysUser);
        rd.setCode("1");
        rd.setMessage("修改密码成功！");
        return rd;
    }
}
