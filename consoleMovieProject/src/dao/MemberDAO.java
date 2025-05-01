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

	// 회원가입 기능
    public void signup(MemberVO vo) {
        String sql = "INSERT INTO MEMBERS(MEMBER_ID, PASSWORD, NICKNAME, IS_ADMIN) VALUES (?, ?, ?, ?)";
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
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }

    // 로그인 기능
    public MemberVO login(String id, String pw) {
        String sql = "SELECT * FROM MEMBERS WHERE MEMBER_ID = ? AND PASSWORD = ?";
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
        } finally {
            JDBCClose.close(conn, pstmt);
        }

        return vo;
    }

    // 모든 멤버 조회(관리자용)
    public List<MemberVO> selectAll() {
        List<MemberVO> list = new ArrayList<>();
        String sql = "SELECT * FROM MEMBERS ORDER BY CREATED_AT DESC";
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
        } finally {
            JDBCClose.close(conn, pstmt);
        }

        return list;
    }
    
    //닉네임 수정
    public void updateNickname(String memberId, String newNickname) {
        String sql = "UPDATE MEMBERS SET NICKNAME = ? WHERE MEMBER_ID = ?";
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
    
    //비번 변경
    public void updatePassword(String memberId, String newPassword) {
        String sql = "UPDATE MEMBERS SET PASSWORD = ? WHERE MEMBER_ID = ?";
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
    
    // 회원 탈퇴
    public void deleteMember(String memberId) {
        String sql = "DELETE FROM MEMBERS WHERE MEMBER_ID = ?";
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
    
    //특정 회원 정보 조회
    public MemberVO selectById(String memberId) {
        String sql = "SELECT * FROM MEMBERS WHERE MEMBER_ID = ?";
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
