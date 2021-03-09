package org.neuedu.servlet0304;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyHttpServlet implements Servlet {
    @Override
    public String getServletInfo() {
        return null;
    }
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    public MyHttpServlet() {
        System.out.println("构造器");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("初始化");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        ServletResponseWrapper response1 = (ServletResponseWrapper) response;
        service(request1,response1);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equals("GET")) {
            doGet();
        } else if (method.equals("POST")) {
            doPost();
        }
    }

    public void doGet() {

    }

    public void doPost() {

    }

    @Override
    public void destroy() {
        System.out.println("销毁方法");
    }
}
