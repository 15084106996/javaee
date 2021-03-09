package org.neuedu.servlet0226;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "LoginServlet03",value = "/login3")
public class LoginServlet03 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数 username tom password 123
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // 设置响应类型
        response.setContentType("application/json;charset=utf-8");
//        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter writer = response.getWriter();
        RespBean respBean = new RespBean();
        if (username.equals("tom") && password.equals("123")) {
            // 登录成功
//            writer.println("<h3>登录成功</h3>");
            User user = new User();
            user.setId(101);
            user.setUsername("tom");

            respBean.setStatus(200);
            respBean.setMsg("登录成功");
            respBean.setObj(user);

        }else{
//            writer.println("登录失败");
            respBean.setStatus(500);
            respBean.setMsg("用户名或密码无效");
        }
        // respBean 写成 JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String strJson = objectMapper.writeValueAsString(respBean);
        writer.println(strJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
