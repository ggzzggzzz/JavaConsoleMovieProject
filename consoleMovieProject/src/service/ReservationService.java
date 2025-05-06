package service;

import java.util.List;
import dao.ReservationDAO;
import vo.ReservationVO;

public class ReservationService {

    private static final ReservationService instance = new ReservationService();
    private ReservationDAO reservationDao;

    private ReservationService() {
        reservationDao = ReservationDAO.getInstance();
    }

    public static ReservationService getInstance() {
        return instance;
    }

    public void addReservation(ReservationVO reservation) {
        reservationDao.insert(reservation);
    }

    public void cancelReservation(int reservationId) {
        reservationDao.cancel(reservationId);
    }

    public List<ReservationVO> getReservationsByMember(String memberId) {
        return reservationDao.selectByMember(memberId);
    }

    public List<String> getReservedSeats(int movieId, String showTime) {
        return reservationDao.getReservedSeats(movieId, showTime);
    }

    public ReservationVO getReservationById(int reservationId) {
        return reservationDao.selectById(reservationId);
    }
}
