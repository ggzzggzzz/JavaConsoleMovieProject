package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.JDBCClose;
import vo.MovieVO;

public class MovieDAO {
	
	 private static final MovieDAO instance = new MovieDAO();

	    private MovieDAO() {}

	    public static MovieDAO getInstance() {
	        return instance;
	    }

	// 영화 등록(관리자용)
	    public void insert(MovieVO vo) {
	        String sql = "INSERT INTO MOVIES(MOVIE_ID, TITLE, GENRE, DIRECTOR, RUNNING_TIME, RELEASE_DATE, SYNOPSIS) "
	                   + "VALUES (seq_movie_id.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = new ConnectionFactory().getConnection();
	            pstmt = conn.prepareStatement(sql);

	            pstmt.setString(1, vo.getTitle());
	            pstmt.setString(2, vo.getGenre());
	            pstmt.setString(3, vo.getDirector());
	            pstmt.setInt(4, vo.getRunningTime());
	            pstmt.setString(5, vo.getReleaseDate());
	            pstmt.setString(6, vo.getSynopsis());

	            pstmt.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            JDBCClose.close(conn, pstmt);
	        }
	    }


    // 영화삭제(관리자용)
    public void delete(int movieId) {
        String sql = "DELETE FROM MOVIES WHERE MOVIE_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }

    // 모든영화 조회
    public List<MovieVO> selectAll() {
        List<MovieVO> list = new ArrayList<>();
        String sql = "SELECT * FROM MOVIES ";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                MovieVO vo = new MovieVO(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("director"),
                    rs.getInt("running_time"),
                    rs.getString("release_date"),
                    rs.getString("synopsis"),
                    rs.getDate("created_at").toString()
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
    
    //특정 영화 조회

    public MovieVO selectById(int movieId) {
        String sql = "SELECT * FROM MOVIES WHERE MOVIE_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        MovieVO vo = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                vo = new MovieVO(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("director"),
                    rs.getInt("running_time"),
                    rs.getString("release_date"),
                    rs.getString("synopsis"),
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
    
    //제목으로 영화 검색

    public List<MovieVO> searchByTitle(String keyword) {
        List<MovieVO> list = new ArrayList<>();
        String sql = "SELECT * FROM MOVIES WHERE TITLE LIKE ? ORDER BY CREATED_AT DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MovieVO vo = new MovieVO(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("director"),
                    rs.getInt("running_time"),
                    rs.getString("release_date"),
                    rs.getString("synopsis"),
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

    //장르로 영화 검색
    public List<MovieVO> serchByGenre(String genre) {
        List<MovieVO> list = new ArrayList<>();
        String sql = "SELECT * FROM MOVIES WHERE GENRE = ? ORDER BY CREATED_AT DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, genre);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MovieVO vo = new MovieVO(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("director"),
                    rs.getInt("running_time"),
                    rs.getString("release_date"),
                    rs.getString("synopsis"),
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
}
