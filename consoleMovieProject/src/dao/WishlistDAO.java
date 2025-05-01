package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.JDBCClose;
import vo.WishlistVO;

public class WishlistDAO {

    // 찜 추가
    public void insert(WishlistVO vo) {
        String sql = "INSERT INTO WISHLISTS(WISHLIST_ID, MEMBER_ID, MOVIE_ID) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, vo.getWishlistId());
            pstmt.setString(2, vo.getMemberId());
            pstmt.setInt(3, vo.getMovieId());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }

    // 찜 삭제
    public void delete(String memberId, int movieId) {
        String sql = "DELETE FROM WISHLISTS WHERE MEMBER_ID = ? AND MOVIE_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, memberId);
            pstmt.setInt(2, movieId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }

    // 찜한 영화 목록 보기 
    public List<WishlistVO> selectByMember(String memberId) {
        List<WishlistVO> list = new ArrayList<>();
        String sql = "SELECT * FROM WISHLISTS WHERE MEMBER_ID = ? ORDER BY CREATED_AT DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                WishlistVO vo = new WishlistVO(
                    rs.getInt("wishlist_id"),
                    rs.getString("member_id"),
                    rs.getInt("movie_id"),
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

    // 이미 찜한 영화인지 확인(중복 방지)
    public boolean isWishlisted(String memberId, int movieId) {
        String sql = "SELECT COUNT(*) AS cnt FROM WISHLISTS WHERE MEMBER_ID = ? AND MOVIE_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean exists = false;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, memberId);
            pstmt.setInt(2, movieId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = rs.getInt("cnt") > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }

        return exists;
    }
}
