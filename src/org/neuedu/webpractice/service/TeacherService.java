package org.neuedu.webpractice.service;

import org.neuedu.webpractice.bean.PageInfos;
import org.neuedu.webpractice.bean.Teacher;
import org.neuedu.webpractice.dao.TeacherDao;
import org.neuedu.webpractice.dao.TeacherDaoImpl;

import java.util.List;

public class TeacherService {
    TeacherDao dao = new TeacherDaoImpl();
    public PageInfos searchTeachers(String idOrName, String pageNum, String pageSize) {
        // 查询一页的真实数据
        List<Teacher> list = dao.findTeacher(idOrName == null?"":idOrName,(Long.valueOf(pageNum)-1)*Long.valueOf(pageSize),pageSize);
        // 查询总数
        long counts = dao.getTotal(idOrName);
        PageInfos infos = new PageInfos();
        infos.setTotal(counts);
        infos.setData(list);
        return infos;
    }
}
