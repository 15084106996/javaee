package org.neuedu.servlet0303.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neuedu.servlet0303.model.Userinfos;
import org.neuedu.servlet0303.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet(name = "FindUserLikeServlet",value = "/findUser")
public class FindUserLikeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String keyWord = request.getParameter("keyWord");
        UserService service = new UserService();
        List<Userinfos> list = service.findUserByNickname(keyWord);
        writer.println(new ObjectMapper().writeValueAsString(list));
    }
}
