package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.JDBCClose;
import vo.MemberVO;

public class MemberDAO {

    public void signup(MemberVO vo) {
        String sql = "INSERT INTO members(member_id, password, nickname, is_admin) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        		
        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
         
            pstmt.setString(1, vo.getMemberId());
            pstmt.setString(2, vo.getPassword());
            pstmt.setString(3, vo.getNickname());
            pstmt.setString(4, vo.getIsAdmin());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			JDBCClose.close(conn, pstmt);
		}
    }

    public MemberVO login(String id, String pw) {
        String sql = "SELECT * FROM members WHERE member_id = ? AND password = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        MemberVO vo = null;

        try {
            conn = new ConnectionFactory().getConnection();
             pstmt = conn.prepareStatement(sql);
         
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                vo = new MemberVO(
                    rs.getString("member_id"),
                    rs.getString("password"),
                    rs.getString("nickname"),
                    rs.getString("is_admin"),
                    rs.getString("created_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			JDBCClose.close(conn, pstmt);
		}

        return vo;
    }

    public List<MemberVO> selectAll() {
        List<MemberVO> list = new ArrayList<>();
        String sql = "SELECT * FROM members ORDER BY created_at DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
             pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
         
            while (rs.next()) {
                MemberVO vo = new MemberVO(
                    rs.getString("member_id"),
                    rs.getString("password"),
                    rs.getString("nickname"),
                    rs.getString("is_admin"),
                    rs.getString("created_at")
                );
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			JDBCClose.close(conn, pstmt);
		}

        return list;
    }
    
    public void updateNickname(String memberId, String newNickname) {
        String sql = "UPDATE members SET nickname = ? WHERE member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newNickname);
            pstmt.setString(2, memberId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }
    
    public void updatePassword(String memberId, String newPassword) {
        String sql = "UPDATE members SET password = ? WHERE member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, memberId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }
    
    public void deleteMember(String memberId) {
        String sql = "DELETE FROM members WHERE member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }
    
    public MemberVO selectById(String memberId) {
        String sql = "SELECT * FROM members WHERE member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        MemberVO vo = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                vo = new MemberVO(
                    rs.getString("member_id"),
                    rs.getString("password"),
                    rs.getString("nickname"),
                    rs.getString("is_admin"),
                    rs.getString("created_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }

        return vo;
    }

}
