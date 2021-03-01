package org.neuedu.servlet0226;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "LoginServlet02",value = "/login2")
public class LoginServlet02 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println(Arrays.asList(hobbies));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println(Arrays.asList(hobbies));
    }
}
