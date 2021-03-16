package org.neuedu.webpractice.dao;

import org.neuedu.webpractice.bean.ClassTeam;
import org.neuedu.webpractice.bean.Teacher;
import org.neuedu.webpractice.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassDaoImpl implements ClassDao {
    @Override
    public List<ClassTeam> findClassInfos(String cidOrTname) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ClassTeam> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select c.id,cname,opentime,closetime,`type`,t.id as tid,tname\n" +
                    "from classteam c\n" +
                    "join teacher_class tc\n" +
                    "on c.id = tc.cid\n" +
                    "join teacher t\n" +
                    "on t.id = tc.tid where c.id = ? or tname like concat('%',?,'%')");
            ps.setString(1,cidOrTname);
            ps.setString(2,cidOrTname);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClassTeam classTeam = new ClassTeam();
                classTeam.setId(rs.getLong("id"));
                classTeam.setCname(rs.getString("cname"));
                classTeam.setOpenTime(rs.getTimestamp("opentime"));
                classTeam.setCloseTime(rs.getTimestamp("closetime"));
                classTeam.setType(rs.getInt("type"));
                Teacher teacher = new Teacher();
                teacher.setId(rs.getLong("tid"));
                teacher.setTname(rs.getString("tname"));
                classTeam.setTeacher(teacher);
                list.add(classTeam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return list;
    }

    @Override
    public List<ClassTeam> findActiveClassByTid(String tid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ClassTeam> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select *\n" +
                    "from classteam\n" +
                    "where id in (select cid from teacher_class where tid = ?)");
            ps.setInt(1,Integer.valueOf(tid));
            rs = ps.executeQuery();
            while (rs.next()) {
                ClassTeam classTeam = new ClassTeam();
                classTeam.setId(rs.getLong("id"));
                classTeam.setCname(rs.getString("cname"));
                classTeam.setOpenTime(rs.getTimestamp("opentime"));
                classTeam.setCloseTime(rs.getTimestamp("closetime"));
                classTeam.setType(rs.getInt("type"));
                list.add(classTeam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return list;
    }

    @Override
    public int addClass(String cname, String type){
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("insert into classteam values (default,?,default,default,?)");
            ps.setString(1, cname);
            ps.setInt(2,Integer.valueOf(type));
            count = ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }

    @Override
    public int getMaxCid() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int max = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select max(id) as maxid from classteam");
            rs = ps.executeQuery();
            if (rs.next()) {
                max = rs.getInt("maxid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return max;
    }

    @Override
    public int insertRelationWithClass(int max, String tid) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("insert into teacher_class values (default,?,?)");
            ps.setInt(1, max);
            ps.setInt(2,Integer.valueOf(tid));
            count = ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }

    @Override
    public ClassTeam checkClassFinishedOrNot(ClassTeam team) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ClassTeam classTeam = null;
        try {
            conn = DBUtils.getInstance().getConnection();
            ps = conn.prepareStatement("select * from classteam where id = ?");
            ps.setLong(1,team.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                classTeam = new ClassTeam();
                classTeam.setId(rs.getLong("id"));
                classTeam.setCname(rs.getString("cname"));
                classTeam.setType(rs.getInt("type"));
                classTeam.setCloseTime(rs.getTimestamp("closetime"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return classTeam;
    }

    @Override
    public int updateLeaveTime(Date date,Long cid) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("update classteam set closetime = ? where id = ?");
            if (date == null) {
                ps.setTimestamp(1, null);
            }else{
                ps.setTimestamp(1, new Timestamp(date.getTime()));
            }
            ps.setLong(2,cid);
            count = ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }

    @Override
    public int updateTeacherClassInfo(ClassTeam team) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("update teacher_class set tid = ? where cid = ?");
            ps.setLong(1,team.getTeacher().getId());
            ps.setLong(2,team.getId());
            count = ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }
}
