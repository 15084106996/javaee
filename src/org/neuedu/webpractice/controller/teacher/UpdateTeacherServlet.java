package org.neuedu.webpractice.controller.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neuedu.webpractice.bean.RespBean;
import org.neuedu.webpractice.bean.Teacher;
import org.neuedu.webpractice.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "UpdateTeacherServlet", value = "/updateTeacherInfo")
public class UpdateTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            String tname = request.getParameter("tname");
            String nation = request.getParameter("nation");
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");
            String mobile = request.getParameter("mobile");
            String educate = request.getParameter("educate");
            String school = request.getParameter("school");
            String status = request.getParameter("status");
            // 封装
            Teacher teacher = new Teacher();
            teacher.setId(Long.valueOf(id));
            teacher.setTname(tname);
            teacher.setNation(nation);
            teacher.setGender(Boolean.valueOf(gender));
            teacher.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
            teacher.setMobile(mobile);
            teacher.setEducate(educate);
            teacher.setSchool(school);
            teacher.setStatus(Boolean.valueOf(status));
            TeacherService service = new TeacherService();
            RespBean respBean = service.updateTeacherInfo(teacher);
            response.getWriter().println(new ObjectMapper().writeValueAsString(respBean));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
