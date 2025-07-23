package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.ConnectionFactory;
import util.JDBCClose;
import vo.ReviewLikeVO;

public class ReviewLikeDAO implements IReviewLikeDAO {

    private static final ReviewLikeDAO instance = new ReviewLikeDAO();

    private ReviewLikeDAO() {}

    public static ReviewLikeDAO getInstance() {
        return instance;
    }

    @Override
    public boolean checkLike(int reviewId, String memberId) {
        String sql = "SELECT COUNT(*) FROM REVIEW_LIKES WHERE REVIEW_ID = ? AND MEMBER_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean exists = false;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reviewId);
            pstmt.setString(2, memberId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
        return exists;
    }

    @Override
    public void addLike(ReviewLikeVO vo) {
        String sql = "INSERT INTO REVIEW_LIKES(REVIEW_ID, MEMBER_ID) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getReviewId());
            pstmt.setString(2, vo.getMemberId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }

    @Override
    public void removeLike(ReviewLikeVO vo) {
        String sql = "DELETE FROM REVIEW_LIKES WHERE REVIEW_ID = ? AND MEMBER_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getReviewId());
            pstmt.setString(2, vo.getMemberId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }
}
