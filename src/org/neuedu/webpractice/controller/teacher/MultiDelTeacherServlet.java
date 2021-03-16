package org.neuedu.webpractice.controller.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neuedu.webpractice.bean.RespBean;
import org.neuedu.webpractice.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MultiDelTeacherServlet",value="/multiDeleteTeacher")
public class MultiDelTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids = request.getParameterValues("id");
        TeacherService service = new TeacherService();
        RespBean respBean = service.multiDeleteTeacherInfos(ids);
        response.getWriter().println(new ObjectMapper().writeValueAsString(respBean));
    }
}
