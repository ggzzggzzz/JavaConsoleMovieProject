package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import util.JDBCClose;
import vo.ReservationVO;

public class ReservationDAO {

    // 1. 예매 등록
    public void insert(ReservationVO vo) {
        String sql = "INSERT INTO RESERVATIONS(RESERVATION_ID, MEMBER_ID, MOVIE_ID, SHOW_TIME, SEAT_NUMBER, PAYMENT_METHOD, PAYMENT_AMOUNT) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, vo.getReservationId());
            pstmt.setString(2, vo.getMemberId());
            pstmt.setInt(3, vo.getMovieId());
            pstmt.setString(4, vo.getShowTime());  // 날짜는 String으로 처리
            pstmt.setString(5, vo.getSeatNumber());
            pstmt.setString(6, vo.getPaymentMethod());
            pstmt.setInt(7, vo.getPaymentAmount());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }

    // 2. 예매 취소 (상태만 변경)
    public void cancel(int reservationId) {
        String sql = "UPDATE RESERVATIONS SET RESERVATION_STATUS = 'CANCELLED' WHERE RESERVATION_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reservationId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }
    }

    // 3. 사용자별 예매 내역 조회
    public List<ReservationVO> selectByMember(String memberId) {
        List<ReservationVO> list = new ArrayList<>();
        String sql = "SELECT * FROM RESERVATIONS WHERE MEMBER_ID = ? ORDER BY RESERVED_AT DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ReservationVO vo = new ReservationVO(
                    rs.getInt("reservation_id"),
                    rs.getString("member_id"),
                    rs.getInt("movie_id"),
                    rs.getString("show_time"),
                    rs.getString("seat_number"),
                    rs.getString("payment_method"),
                    rs.getInt("payment_amount"),
                    rs.getString("reservation_status"),
                    rs.getString("reserved_at")
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

    // 4. 특정 영화의 예매 좌석 목록 조회 (상영 시간 기준)
    public List<String> getReservedSeats(int movieId, String showTime) {
        List<String> seats = new ArrayList<>();
        String sql = "SELECT SEAT_NUMBER FROM RESERVATIONS WHERE MOVIE_ID = ? AND SHOW_TIME = ? AND RESERVATION_STATUS = 'RESERVED'";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);
            pstmt.setString(2, showTime);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                seats.add(rs.getString("seat_number"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCClose.close(conn, pstmt);
        }

        return seats;
    }

    // 5. 예매 상세 조회
    public ReservationVO selectById(int reservationId) {
        String sql = "SELECT * FROM RESERVATIONS WHERE RESERVATION_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ReservationVO vo = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reservationId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                vo = new ReservationVO(
                    rs.getInt("reservation_id"),
                    rs.getString("member_id"),
                    rs.getInt("movie_id"),
                    rs.getString("show_time"),
                    rs.getString("seat_number"),
                    rs.getString("payment_method"),
                    rs.getInt("payment_amount"),
                    rs.getString("reservation_status"),
                    rs.getString("reserved_at")
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
