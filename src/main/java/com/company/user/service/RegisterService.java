package com.company.user.service;

import com.company.common.ResultData;
import com.company.common.constant.SysParameter;
import com.company.common.dao.IBaseDao;
import com.company.common.util.StringUtil;
import com.company.user.model.SysUser;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 注册处理类service
 */
@Service("registerService")
public class RegisterService {

    private static Logger log = Logger.getLogger(RegisterService.class);

    @Resource
    private IBaseDao baseDao;

    private static final String nameSpace = "N_SYS_USER.";

    /**
     * 注册新用户
     *
     * @param request
     * @param sysUser
     * @return
     */
    public ResultData register(HttpServletRequest request, SysUser sysUser){
        ResultData rd = new ResultData();
        String userName = sysUser.getUserName();
        if(StringUtils.isEmpty(userName)){
            rd.setCode("-1");
            rd.setMessage("登录用户名称不能为空！");
            return rd;
        }
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

        String id = StringUtil.getUUID();
        sysUser.setUserId(id);
        this.baseDao.insert(nameSpace + "addSysUser", sysUser);
        // 将登录信息存入session中
        HttpSession session = request.getSession(true); // 若存在会话则返回该会话，否则新建一个会话。
        session.setAttribute(SysParameter.LOGIN_SESSION, sysUser);

        rd.setCode("1");
        rd.setMessage("注册成功！");
        return rd;
    }
}
