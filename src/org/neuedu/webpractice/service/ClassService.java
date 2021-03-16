package org.neuedu.webpractice.service;

import org.neuedu.webpractice.bean.ClassTeam;
import org.neuedu.webpractice.bean.RespBean;
import org.neuedu.webpractice.dao.ClassDao;
import org.neuedu.webpractice.dao.ClassDaoImpl;

import java.util.Date;
import java.util.List;

public class ClassService {
    ClassDao dao = new ClassDaoImpl();

    public List<ClassTeam> findClassInfos(String cidOrTname) {
        return dao.findClassInfos(cidOrTname == null ? "" : cidOrTname);
    }

    public RespBean addClass(String cname, String type, String tid) {
        // 查找tid对应的教师有没有班级
        List<ClassTeam> list = dao.findActiveClassByTid(tid);
        for (ClassTeam classTeam : list) {
            if (classTeam.getCloseTime() == null) {
                return RespBean.error(500, "教师资源被占用");
            }
        }
        int i = dao.addClass(cname, type);
        if (i == 0) {
            return RespBean.error(500, "新增失败");
        } else {
            // 查询班级最大的id
            int max = dao.getMaxCid();
            dao.insertRelationWithClass(max, tid);
            return RespBean.success(200, "新增成功");
        }
    }

    public RespBean updateClassInfo(ClassTeam team) {
        // 根据 id 查找班级
        ClassTeam reTeam = dao.checkClassFinishedOrNot(team);
        if (reTeam == null) {
            return RespBean.error(500, "没有找到班级");
        } else {
            // 根据 制定老师 查询 所带班级
            List<ClassTeam> classTeams = dao.findActiveClassByTid(team.getTeacher().getId() + "");
            boolean flag = true;
            // 判断 班级是否全毕业，或者与当前班级id相等
            for (ClassTeam classTeam : classTeams) {
                if (classTeam.getCloseTime() == null && classTeam.getId().longValue() != team.getId().longValue()) {
                    flag = false;
                }
            }
            if (flag) {
                if (reTeam.getCloseTime() != null) {
                    if (!team.getYesOrNot()) {
                        // 清空毕业时间
                        dao.updateLeaveTime(null,team.getId());
                    }
                } else {
                    // 如果班级未毕业
                    if (team.getYesOrNot()) {
                        // 赋值一个毕业时间
                        dao.updateLeaveTime(new Date(),team.getId());
                    }
                }
                // 更新关联信息
                dao.updateTeacherClassInfo(team);
                return RespBean.success(200, "修改班级信息成功");
            } else {
                return RespBean.error(500, "教师资源被占用，请选择其他教师");
            }
        }
    }
}
