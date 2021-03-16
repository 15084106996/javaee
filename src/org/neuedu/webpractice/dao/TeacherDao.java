package org.neuedu.webpractice.dao;

import org.neuedu.webpractice.bean.Teacher;

import java.util.List;

public interface TeacherDao {
    // 分页数据
    List<Teacher> findTeacher(String idOrName, long pageNum, String pageSize);
    // 查询总数据
    long getTotal(String idOrName);
    // 修改教师信息
    int updateTeacherInfo(Teacher teacher);
    // 修改离职时间
    int updateLeaveTime(Teacher teacher);
    // 根据 id 查询教师在职状态
    Teacher serchTeacherStatusById(Teacher teacher);
    // 根据id删除教师信息
    int delTeacherById(Long id);
    // 批量删除
    int multiDeleteTeacherInfos(String params);
    // 查找所有在职教师
    List<Teacher> serchIdelTeacher();
}
