package org.neuedu.servlet0303.dao;

import org.neuedu.servlet0303.model.Userinfos;

import java.util.List;

public interface UserDao {
    // 新增用户
    int addUser(Userinfos userinfos);

    // 删除用户
    int delUserById(String id);

    // 修改用户
    int updateUserInfo(Userinfos userinfos);

    // 模糊查询
    List<Userinfos> findUserByNickname(String nickname);
}
