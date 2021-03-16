package org.neuedu.webpractice.controller.classteam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neuedu.webpractice.bean.ClassTeam;
import org.neuedu.webpractice.bean.RespBean;
import org.neuedu.webpractice.bean.Teacher;
import org.neuedu.webpractice.service.ClassService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateClassInfoServlet",value="/updateClassInfo")
public class UpdateClassInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String cname = request.getParameter("cname");
        String type = request.getParameter("type");
        String tid = request.getParameter("tid");
        String yesOrNot = request.getParameter("yesOrNot");
        // 封装数据
        Teacher teacher = new Teacher();
        teacher.setId(Long.valueOf(tid));
        ClassTeam team = new ClassTeam();
        team.setTeacher(teacher);
        team.setId(Long.valueOf(id));
        team.setCname(cname);
        team.setType(Integer.valueOf(type));
        team.setYesOrNot(Boolean.valueOf(yesOrNot));
        ClassService service = new ClassService();
        RespBean respBean = service.updateClassInfo(team);
        response.getWriter().println(new ObjectMapper().writeValueAsString(respBean));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
