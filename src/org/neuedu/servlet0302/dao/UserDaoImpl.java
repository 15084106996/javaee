package org.neuedu.servlet0302.dao;

import org.neuedu.servlet0302.entity.User;
import org.neuedu.servlet0302.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getAllUser() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "select * from user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setBirthday(rs.getDate("birthday"));
                user.setGender(rs.getBoolean("gender"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return list;
    }
}
