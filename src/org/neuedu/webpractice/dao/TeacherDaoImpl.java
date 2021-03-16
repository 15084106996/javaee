package org.neuedu.webpractice.dao;

import org.neuedu.webpractice.bean.Teacher;
import org.neuedu.webpractice.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public List<Teacher> findTeacher(String idOrName, long pageNum, String pageSize) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teacher> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select * from teacher where delmark = 1 and (id = ? or tname like concat('%',?,'%')) limit ?,?");
            ps.setString(1,idOrName);
            ps.setString(2,idOrName);
            ps.setLong(3,pageNum);
            ps.setLong(4,Long.valueOf(pageSize));
            rs = ps.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getLong("id"));
                teacher.setTname(rs.getString("tname"));
                teacher.setNation(rs.getString("nation"));
                teacher.setGender(rs.getBoolean("gender"));
                teacher.setBirthday(rs.getDate("birthday"));
                teacher.setMobile(rs.getString("mobile"));
                teacher.setEducate(rs.getString("educate"));
                teacher.setSchool(rs.getString("school"));
                teacher.setJoinTime(rs.getTimestamp("jointime"));
                teacher.setLeaveTime(rs.getTimestamp("leavetime"));
                teacher.setStatus(rs.getBoolean("status"));
                teacher.setDelMark(rs.getBoolean("delmark"));
                list.add(teacher);
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

    @Override
    public long getTotal(String idOrName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select count(id) as total from teacher where delmark = 1 and (id = ? or tname like concat('%',?,'%'))");
            ps.setString(1,idOrName);
            ps.setString(2,idOrName);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }

    @Override
    public int updateTeacherInfo(Teacher teacher) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("update teacher set tname = ?,nation=?,gender=?,birthday=?,mobile=?,educate=?,school=?,status=? where id = ?");
            ps.setString(1, teacher.getTname());
            ps.setString(2, teacher.getNation());
            ps.setBoolean(3, teacher.getGender());
            ps.setTimestamp(4, new Timestamp(teacher.getBirthday().getTime()));
            ps.setString(5, teacher.getMobile());
            ps.setString(6, teacher.getEducate());
            ps.setString(7, teacher.getSchool());
            ps.setBoolean(8, teacher.getStatus());
            ps.setLong(9, teacher.getId());
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
    public int updateLeaveTime(Teacher teacher) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "update teacher set leavetime = ? where id = ?";
            ps = conn.prepareStatement(sql);
            if (teacher.getStatus() == true) {
                ps.setDate(1,null);
            }else{
                ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            }
            ps.setLong(2,teacher.getId());
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
    public Teacher serchTeacherStatusById(Teacher teacher) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teacher t = null;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select status from teacher where delmark = 1 and id = ?");
            ps.setLong(1, teacher.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                t = new Teacher();
                t.setStatus(rs.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return t;
    }

    @Override
    public int delTeacherById(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "update teacher set delmark = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setLong(2, id);
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
    public int multiDeleteTeacherInfos(String params) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "update teacher set delmark = ? where id in "+params;
            ps = conn.prepareStatement(sql);
            ps.setInt(1,0);
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
    public List<Teacher> serchIdelTeacher() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teacher> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select * from teacher where status = ? and delmark = ?");
            ps.setInt(1,1);
            ps.setInt(2,1);
            rs = ps.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getLong("id"));
                teacher.setTname(rs.getString("tname"));
                list.add(teacher);
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
