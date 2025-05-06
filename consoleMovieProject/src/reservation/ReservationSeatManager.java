package reservation;

import java.util.*;

import service.MovieService;
import vo.MovieVO;

public class ReservationSeatManager {
    private static final int ROWS = 5;
    private static final int COLS = 5;
    private static Map<Integer, boolean[][]> seatMap = new HashMap<>();

    public static boolean[][] getSeats(int movieId) {
        return seatMap.computeIfAbsent(movieId, k -> new boolean[ROWS][COLS]);
    }

    public static void printSeats(int movieId) {
        boolean[][] seats = getSeats(movieId);
        System.out.println("\n좌석 현황 (O: 비어있음, X: 예매됨)");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(seats[i][j] ? "X " : "O ");
            }
            System.out.println();
        }
    }

    public static boolean reserveSeat(int movieId, int row, int col) {
        boolean[][] seats = getSeats(movieId);
        if (!seats[row][col]) {
            seats[row][col] = true;
            return true;
        }
        return false;
    }
}