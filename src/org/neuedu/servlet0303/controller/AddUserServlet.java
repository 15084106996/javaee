package org.neuedu.servlet0303.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neuedu.servlet0303.model.RespBean;
import org.neuedu.servlet0303.model.Userinfos;
import org.neuedu.servlet0303.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "AddUserServlet",value = "/addUser")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        // 获取请求参数
        String username = request.getParameter("name");
        String password = request.getParameter("pass");
        String nickname = request.getParameter("nick");
        // 封装数据
        Userinfos userinfos = new Userinfos();
        userinfos.setUsername(username);
        userinfos.setPassword(password);
        userinfos.setNickname(nickname);
        // 调Service
        UserService userService = new UserService();
        RespBean respBean = userService.addUser(userinfos);
        writer.println(new ObjectMapper().writeValueAsString(respBean));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
