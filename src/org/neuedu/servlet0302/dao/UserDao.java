package org.neuedu.servlet0302.dao;

import org.neuedu.servlet0302.entity.User;

import java.util.List;

public interface UserDao {
    // 查询所有用户
    List<User> getAllUser();
}
