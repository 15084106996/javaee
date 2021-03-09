package org.neuedu.webpractice.dao;

import org.neuedu.webpractice.bean.SysUser;
import org.neuedu.webpractice.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
