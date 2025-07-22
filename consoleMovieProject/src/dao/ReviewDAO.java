package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.JDBCClose;
import vo.ReviewVO;

public class ReviewDAO implements IReviewDAO {
	
	 private static final ReviewDAO instance = new ReviewDAO();

	    private ReviewDAO() {}

	    public static ReviewDAO getInstance() {
	        return instance;
	    }

    // 리뷰 작성
    @Override
    public void insert(ReviewVO vo) {
    	String sql = "INSERT INTO REVIEWS(REVIEW_ID, MEMBER_ID, MOVIE_ID, RATING, CONTENT) "
    	           + "VALUES (seq_review_id.NEXTVAL, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vo.getMemberId());
            pstmt.setInt(2, vo.getMovieId());
            pstmt.setDouble(3, vo.getRating());
            pstmt.setString(4, vo.getContent());


            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }

    // 리뷰 수정
    @Override
    public void update(ReviewVO vo) {
        String sql = "UPDATE REVIEWS SET CONTENT = ?, RATING = ? WHERE REVIEW_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getContent());
            pstmt.setDouble(2, vo.getRating());
            pstmt.setInt(3, vo.getReviewId());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }

    // 리뷰 삭제
    @Override
    public void delete(int reviewId) {
        String sql = "DELETE FROM REVIEWS WHERE REVIEW_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reviewId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }

    // 특정 영화의 리뷰 목록
    @Override
    public List<ReviewVO> selectByMovieId(int movieId) {
        List<ReviewVO> list = new ArrayList<>();
        String sql = "SELECT * FROM REVIEWS WHERE MOVIE_ID = ? ORDER BY CREATED_AT DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ReviewVO vo = new ReviewVO(
                    rs.getInt("review_id"),
                    rs.getString("member_id"),
                    rs.getInt("movie_id"),
                    rs.getDouble("rating"),
                    rs.getString("content"),
                    rs.getInt("like_count"),
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

    // 내가 쓴 리뷰 보기
    @Override
    public List<ReviewVO> selectByMemberId(String memberId) {
        List<ReviewVO> list = new ArrayList<>();
        String sql = "SELECT * FROM REVIEWS WHERE MEMBER_ID = ? ORDER BY CREATED_AT DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ReviewVO vo = new ReviewVO(
                    rs.getInt("review_id"),
                    rs.getString("member_id"),
                    rs.getInt("movie_id"),
                    rs.getDouble("rating"),
                    rs.getString("content"),
                    rs.getInt("like_count"),
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

    // 좋아요 수 증가
    @Override
    public void increaseLike(int reviewId) {
        String sql = "UPDATE REVIEWS SET LIKE_COUNT = LIKE_COUNT + 1 WHERE REVIEW_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reviewId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }

    // 특정 영화의 평균 평점 계산
    @Override
    public double getAverageRating(int movieId) {
        String sql = "SELECT NVL(AVG(RATING), 0) AS avg_rating FROM REVIEWS WHERE MOVIE_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        double avg = 0.0;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                avg = rs.getDouble("avg_rating");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }

        return avg;
    }
}
