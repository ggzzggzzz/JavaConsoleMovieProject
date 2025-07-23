package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.JDBCClose;
import vo.WishlistVO;

public class WishlistDAO implements IWishlistDAO {

	private static final WishlistDAO instance = new WishlistDAO();

    private WishlistDAO() {}

    public static WishlistDAO getInstance() {
        return instance;
    }
	
    // 찜 추가
    @Override
    public void insert(WishlistVO vo) {
        String sql = "INSERT INTO WISHLISTS(WISHLIST_ID, MEMBER_ID, MOVIE_ID) "
                   + "VALUES (seq_wishlist_id.NEXTVAL, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vo.getMemberId());
            pstmt.setInt(2, vo.getMovieId());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }


    // 찜 삭제
    @Override
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
    @Override
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
    @Override
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

    // 추가된 메소드 구현
    @Override
    public int countWishesByMovieId(int movieId) {
        String sql = "SELECT COUNT(*) FROM WISHLISTS WHERE MOVIE_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }

        return count;
    }
}
