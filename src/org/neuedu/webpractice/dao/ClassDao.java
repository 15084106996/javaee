package org.neuedu.webpractice.dao;

import org.neuedu.webpractice.bean.ClassTeam;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ClassDao {
    List<ClassTeam> findClassInfos(String cidOrTname);
    // 根据 教师 编号 查找 他所带的所有班级
    List<ClassTeam> findActiveClassByTid(String tid);
    // 新增班级
    int addClass(String cname, String type);
    // 查找最大的班级id
    int getMaxCid();
    // 新增班级与教师的关系
    int insertRelationWithClass(int max, String tid);
    // 根据id查询班级
    ClassTeam checkClassFinishedOrNot(ClassTeam team);
    // 更新 毕业 时间
    int updateLeaveTime(Date date,Long cid);
    // 更新 教师 班级 关联信息
    int updateTeacherClassInfo(ClassTeam team);
}
