package org.neuedu.webpractice.service;

import org.neuedu.webpractice.bean.PageInfos;
import org.neuedu.webpractice.bean.RespBean;
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
        long counts = dao.getTotal(idOrName == null?"":idOrName);
        PageInfos infos = new PageInfos();
        infos.setTotal(counts);
        infos.setData(list);
        return infos;
    }

    public RespBean updateTeacherInfo(Teacher teacher) {
        // 查询教师在职状态
        Teacher reTeacher = dao.serchTeacherStatusById(teacher);
        if (reTeacher != null) {
            int count = dao.updateTeacherInfo(teacher);
            if (count == 0) {
                return RespBean.error(500, "修改信息失败");
            }else{
                // 教师在职状态 != teacher状态
                if (reTeacher.getStatus() != teacher.getStatus()) {
                    dao.updateLeaveTime(teacher);
                }
                return RespBean.success(200, "修改信息成功");
            }
        }
        else{
            return RespBean.error(500, "用户不存在");
        }
    }

    public RespBean delTeacherInfo(Long id) {
        int count = dao.delTeacherById(id);
        if (count == 0) {
            return RespBean.error(500, "删除信息失败");
        }else{
            return RespBean.success(200, "删除信息成功");
        }
    }

    public RespBean multiDeleteTeacherInfos(String[] ids) {
        String params = "(";
        for (String id : ids) {
            params += id + ",";
        }
        params = params.substring(0, params.length()-1)+")";
        int count = dao.multiDeleteTeacherInfos(params);
        if (count == 0) {
            return RespBean.error(500, "批量删除失败");
        }else{
            return RespBean.success(200, "批量删除成功");
        }
    }

    public List<Teacher> serchIdelTeacher() {
        return dao.serchIdelTeacher();
    }

}
