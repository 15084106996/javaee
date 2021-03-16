package org.neuedu.webpractice.dao;

import org.neuedu.webpractice.bean.Role;
import org.neuedu.webpractice.bean.SysUser;

import java.util.List;

public interface SysUserDao {
    // 根据 username 和 password 查询用户
    SysUser findUserByNameAndPass(String username, String password);
    // 根据 用户id 查询角色
    List<Role> getRolesById(Long id);
}
