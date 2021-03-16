package org.neuedu.webpractice.controller.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neuedu.webpractice.bean.Teacher;
import org.neuedu.webpractice.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchTeacherIdelServlet",value="/serchIdelTeacher")
public class SearchTeacherIdelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherService service = new TeacherService();
        List<Teacher> teachers = service.serchIdelTeacher();
        response.getWriter().println(new ObjectMapper().writeValueAsString(teachers));
    }
}
