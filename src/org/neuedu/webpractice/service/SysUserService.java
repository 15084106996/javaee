package org.neuedu.webpractice.service;

import org.neuedu.servlet0303.model.RespBean;
import org.neuedu.webpractice.bean.Role;
import org.neuedu.webpractice.bean.SysUser;
import org.neuedu.webpractice.dao.SysUserDao;
import org.neuedu.webpractice.dao.SysUserDaoImpl;
import org.neuedu.webpractice.utils.MD5Utils;

import java.util.List;

public class SysUserService {
    SysUserDao dao = new SysUserDaoImpl();
    public RespBean doLogin(String username, String password) {
        // 密码加密
        SysUser user = dao.findUserByNameAndPass(username, MD5Utils.Md5(username+password));
        if (user == null) {
            return RespBean.error(500, "用户名或密码错误");
        }else{
            // 查询 用户的 角色
            List<Role> roles = dao.getRolesById(user.getId());
            user.setPassword(null);
            user.setRoles(roles);
            return RespBean.success(200, "登录成功",user);
        }
    }
}
