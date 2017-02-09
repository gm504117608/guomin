package com.company.user.service;


import com.company.common.ResultData;
import com.company.common.constant.SysParameter;
import com.company.common.dao.IBaseDao;
import com.company.user.model.SysUser;
import com.company.user.util.CookieTool;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录处理类service
 */
@Service("loginService")
public class LoginService {

    private static Logger log = Logger.getLogger(LoginService.class);

    @Resource
    private IBaseDao baseDao;

    private static final String nameSpace = "N_SYS_USER.";

    private static final int MaxAge = 15*24*60*60; // cookie存储时间15天

    /**
     * 登录校验
     *
     * @param request
     * @param response
     * @param sysUser
     * @return
     */
    public ResultData login(HttpServletRequest request, HttpServletResponse response, SysUser sysUser){
        ResultData rd = new ResultData();
        String userName = sysUser.getUserName();
        if(StringUtils.isEmpty(userName)){
            rd.setCode("-1");
            rd.setMessage("登录用户名称不能为空！");
            return rd;
        }
        String pwd = sysUser.getUserPassword();
        if(StringUtils.isEmpty(pwd)){
            rd.setCode("-1");
            rd.setMessage("登录密码不能为空！");
            return rd;
        }
        SysUser su = this.baseDao.queryOne(nameSpace + "getSysUser", sysUser);
        if(null == su){
            rd.setCode("-1");
            rd.setMessage("登录用户名称错误！");
            return rd;
        }
        String pwd2 = su.getUserPassword();
        if(StringUtils.isEmpty(pwd2) || !pwd.equals(pwd2)){
            rd.setCode("-1");
            rd.setMessage("登录密码错误！");
            return rd;
        }
        // 用户登录勾选记住登录信息
        String rememberPwd = sysUser.getRememberPwd();  // 1：记住密码；0：不记住密码
        if ("1".equals(rememberPwd)) {
            CookieTool.addCookie(response, "userName", userName, MaxAge); //将姓名加入到cookie中
            CookieTool.addCookie(response, "userPassword", pwd, MaxAge);   //将密码加入到cookie中
        }
        // 将登录信息存入session中
        HttpSession session = request.getSession(true); // 若存在会话则返回该会话，否则新建一个会话。
        session.setAttribute(SysParameter.LOGIN_SESSION, su);

        rd.setCode("1");
        rd.setMessage("登录成功！");
        return rd;
    }

}