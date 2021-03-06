package org.neuedu.servlet0303.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(value = {"/addUser","/delById","/findUser","/updateUser"})
@WebFilter(value = "/*")
public class Filter_Ecoding  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("application/json;charset=utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
