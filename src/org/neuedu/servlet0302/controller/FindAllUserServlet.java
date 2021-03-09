package org.neuedu.servlet0302.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neuedu.servlet0302.entity.User;
import org.neuedu.servlet0302.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "FindAllUserServlet",value = "/findAll")
public class FindAllUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        UserService userService = new UserService();
        List<User> allUsers = userService.getAllUsers();
        response.getWriter().println(new ObjectMapper().writeValueAsString(allUsers));
    }
}
