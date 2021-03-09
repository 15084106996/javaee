package org.neuedu.servlet0304;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "FindAllUserServlet2",value = "/find03041")
public class FindAllUserServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 响应字符串
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            System.out.println("未登录");
        }else{
            System.out.println("可以访问");
        }
    }
}
