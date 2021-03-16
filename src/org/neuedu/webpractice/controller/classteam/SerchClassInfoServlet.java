package org.neuedu.webpractice.controller.classteam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neuedu.webpractice.bean.ClassTeam;
import org.neuedu.webpractice.service.ClassService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SerchClassInfoServlet",value="/findClassInfos")
public class SerchClassInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidOrTname = request.getParameter("cidOrTname");
        ClassService service = new ClassService();
        List<ClassTeam> classTeams = service.findClassInfos(cidOrTname);
        response.getWriter().println(new ObjectMapper().writeValueAsString(classTeams));
    }
}
