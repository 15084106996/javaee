package org.neuedu.servlet0303.dao;

import org.neuedu.servlet0302.utils.DBUtils;
import org.neuedu.servlet0303.model.Userinfos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    @Override
    public int addUser(Userinfos userinfos) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into userinfos values(default,?,?,?,default,default)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,userinfos.getUsername());
            ps.setString(2,userinfos.getPassword());
            ps.setString(3,userinfos.getNickname());
            count = ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }

    @Override
    public int delUserById(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from userinfos where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,Long.parseLong(id));
            count = ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }

    @Override
    public int updateUserInfo(Userinfos userinfos) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            // 查询
            ps = conn.prepareStatement("select * from userinfos where id = ?");
            ps.setLong(1,userinfos.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                conn.setAutoCommit(false);
                String sql = "update userinfos set gender=?,nickname=?,password=?,birthday=? where id=?";
                ps = conn.prepareStatement(sql);
                ps.setBoolean(1,userinfos.getGender()==null?rs.getBoolean("gender"):userinfos.getGender());
                ps.setString(2,userinfos.getNickname()==null?rs.getString("nickname"):userinfos.getNickname());
                ps.setString(3,userinfos.getPassword()==null?rs.getString("password"):userinfos.getPassword());
                ps.setTimestamp(4,userinfos.getBirthday()==null?new Timestamp(rs.getDate("birthday").getTime()):new Timestamp(userinfos.getBirthday().getTime()));
                ps.setLong(5,userinfos.getId());
                count = ps.executeUpdate();
                conn.commit();
            }
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }

    @Override
    public List<Userinfos> findUserByNickname(String nickname) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Userinfos> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select * from userinfos where nickname like concat('%',?,'%')");
            ps.setString(1,nickname);
            rs = ps.executeQuery();
            while (rs.next()) {
                Userinfos userinfos = new Userinfos();
                userinfos.setId(rs.getLong("id"));
                userinfos.setBirthday(rs.getDate("birthday"));
                userinfos.setGender(rs.getBoolean("gender"));
                userinfos.setPassword(rs.getString("password"));
                userinfos.setNickname(rs.getString("nickname"));
                userinfos.setUsername(rs.getString("username"));
                list.add(userinfos);
            }
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }

        return list;
    }
}
