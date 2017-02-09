package com.company.common.filter;

import com.company.common.ResultData;
import com.company.common.constant.SysParameter;
import com.company.user.model.SysUser;
import com.company.user.service.LoginService;
import com.company.user.util.CookieTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * 拦截所有的请求进行请求前的校验处理
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private LoginService loginService;

    private static final Set filter_url = new HashSet(); // 不需要进行过滤处理的请求

    private static final String LOGIN_PAGE = "toLogin.html"; // 登陆页面

    static {
        filter_url.add("/login.html");
        filter_url.add("/toLogin.html");
        filter_url.add("/register.html");
        filter_url.add("/forget.html");


    }

    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
     * SpringMVC中的Interceptor拦截器是链式的，可以同时存在
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();
        boolean isLogin = ifNeedLogin(uri);
        if (isLogin) { // 需要登录
            HttpSession session = request.getSession(true); // 若存在会话则返回该会话，否则新建一个会话。
            SysUser su = (SysUser) session.getAttribute(SysParameter.LOGIN_SESSION);
            if (null == su) { // 未登录
                String userName = CookieTool.getCookieValueByName(request, "userName");
                String userPassword = CookieTool.getCookieValueByName(request, "userPassword");
                if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(userPassword)) {
                    // 检查到客户端保存了用户的密码，进行该账户的验证
                    SysUser suCookie = new SysUser();
                    suCookie.setUserName(userName);
                    suCookie.setUserPassword(userPassword);
                    suCookie.setRememberPwd("1"); // 1：记住密码；0：不记住密码
                    ResultData rd = loginService.login(request, response, suCookie);
                    if ("-1".equals(rd.getCode())) {
                        CookieTool.addCookie(response, "userName", null, 0); // 清除Cookie
                        CookieTool.addCookie(response, "userPassword", null, 0); // 清除Cookie
                        response.sendRedirect(LOGIN_PAGE);
                        return false;
                    }
                }else{
                    response.sendRedirect(LOGIN_PAGE);
                    return false;
                }
            } else { // 已经登录，判断他是否登录前勾选了记住密码
                response.sendRedirect(LOGIN_PAGE);
                return false;
            }
        }
        return true;
    }

    /**
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。
     * postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之
     * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，
     * 也就是说在这个方法中你可以对ModelAndView进行操
     * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，
     * 这跟Struts2里面的拦截器的执行过程有点像，
     * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，
     * Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
     * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，
     * 要在Interceptor之后调用的内容都写在调用invoke方法之后。
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，
     * 也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 根据uri判断是否需要进行登录之后才可以访问
     * @param uri
     * @return false ： 不需要登录；true ： 需要登录
     */
    private boolean ifNeedLogin(String uri){
        uri=uri.substring(uri.lastIndexOf("/"));
        if(filter_url.contains(uri)){
            return false;
        }else {
            return true;
        }
    }
}
