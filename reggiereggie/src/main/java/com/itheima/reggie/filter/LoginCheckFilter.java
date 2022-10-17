package com.itheima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.common.BaseContext;
import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经完成登录
 */
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher antPathMatcher = new AntPathMatcher ();
    @Override
    //路径匹配器，支持通配符
    //1、获取本次请求的URI
    //2、判断本次请求是否需要处理
    //3、如果不需要处理，则直接放行
    //4、判断登录状态，如果已登录，则直接放行
    //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request =(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        String[] strings=new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        String requestURI = request.getRequestURI ();
        boolean check = check (strings, requestURI);
        if (check){
            filterChain.doFilter (request, response);
            return;
        }
        if (request.getSession ().getAttribute ("employee")!=null){
            BaseContext.setCurrentId ((long) request.getSession ().getAttribute ("employee"));
            filterChain.doFilter (request, response);
            return;
        }else {
            response.getWriter ().write (JSON.toJSONString (R.error ("NOTLOGIN")));
            return;
        }
    }
    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param strings
     * @param url
     * @return
     */

    public boolean check(String[] strings,String url){
        for (String s:strings){
            boolean match = antPathMatcher.match (s, url);
            if (match){
                return true;
            }
        }
        return false;
    }
}
