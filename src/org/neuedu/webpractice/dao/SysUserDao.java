package org.neuedu.webpractice.dao;

import org.neuedu.webpractice.bean.SysUser;

public interface SysUserDao {
    SysUser findUserByNameAndPass(String username, String password);
}
