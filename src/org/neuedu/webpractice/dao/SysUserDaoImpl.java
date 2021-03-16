package org.neuedu.webpractice.dao;

import org.neuedu.webpractice.bean.Role;
import org.neuedu.webpractice.bean.SysUser;
import org.neuedu.webpractice.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SysUserDaoImpl implements SysUserDao {
    @Override
    public SysUser findUserByNameAndPass(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SysUser user = null;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select * from sysuser where username = ? and password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new SysUser();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return user;
    }

    @Override
    public List<Role> getRolesById(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        List<Role> roles = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select *\n" +
                    "from role\n" +
                    "where id in (select rid from user_role where uid = ?)");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRname(rs.getString("rname"));
                role.setRnameZh(rs.getString("rnameZh"));
                roles.add(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return roles;
    }
}
