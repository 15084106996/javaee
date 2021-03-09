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
import java.text.ParseException;
import java.text.SimpleDateFormat;

//@WebServlet(name = "UpdateUserServlet",value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter writer = response.getWriter();
            // 获取参数
            String id = request.getParameter("id");
            String nickname = request.getParameter("nickname");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");
            // 封装
            Userinfos userinfos = new Userinfos();
            if (id != null) {
                userinfos.setId(Long.valueOf(id));
            }
            userinfos.setNickname(nickname);
            userinfos.setPassword(password);
            userinfos.setGender(Boolean.valueOf(gender));
            if (birthday != null) {
                userinfos.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
            }
            UserService service = new UserService();
            RespBean respBean = service.updateUser(userinfos);
            writer.println(new ObjectMapper().writeValueAsString(respBean));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
