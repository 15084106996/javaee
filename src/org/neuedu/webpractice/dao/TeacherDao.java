package org.neuedu.webpractice.dao;

import org.neuedu.webpractice.bean.Teacher;

import java.util.List;

public interface TeacherDao {
    // 分页数据
    List<Teacher> findTeacher(String idOrName, long pageNum, String pageSize);
    // 查询总数据
    long getTotal(String idOrName);
}
