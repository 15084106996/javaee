package org.neuedu.webpractice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neuedu.webpractice.bean.PageInfos;
import org.neuedu.webpractice.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchTeacherServlet",value="/searchTeachers")
public class SearchTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 编号或姓名
        String idOrName = request.getParameter("idOrName");
        // pageNum
        String pageNum = request.getParameter("pageNum");
        // pageSize
        String pageSize = request.getParameter("pageSize");
        TeacherService service = new TeacherService();
        PageInfos pageInfos = service.searchTeachers(idOrName,pageNum,pageSize);
        response.getWriter().println(new ObjectMapper().writeValueAsString(pageInfos));
    }
}
