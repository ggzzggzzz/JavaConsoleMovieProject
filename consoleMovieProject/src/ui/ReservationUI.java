package ui;

import java.util.List;
import java.util.Scanner;

import service.MovieService;
import vo.MemberVO;
import vo.MovieVO;

public class ReservationUI {

    private Scanner sc = new Scanner(System.in);
    private MovieService movieService = MovieService.getInstance();
    private MemberVO loginUser;

    private static final int ROWS = 5;
    private static final int COLS = 5;
    private static final int[][][] seatMap = new int[100][ROWS][COLS]; 

    public ReservationUI(MemberVO loginUser) {
        this.loginUser = loginUser;
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════╗");
            System.out.println("║       🎟️ 예매 메뉴         ║");
            System.out.println("╚══════════════════════════╝");
            System.out.println("[1] 🎬 영화 목록 보기");
            System.out.println("[2] 🎟️ 좌석 예매");
            System.out.println("[3] ❌ 예매 취소");
            System.out.println("[4] 🎞️ 예매 좌석 조회");
            System.out.println("[0] 🔙 뒤로가기");
            System.out.print("👉 선택 > ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ 숫자를 입력해주세요.");
                continue;
            }

            switch (choice) {
                case 1: showMovies(); break;
                case 2: reserveSeat(); break;
                case 3: cancelSeat(); break;
                case 4: viewSeatStatus(); break;
                case 0: return;
                default: System.out.println("⚠️ 잘못된 입력입니다.");
            }
        }
    }

    private void showMovies() {
        List<MovieVO> movies = movieService.getAllMovies();
        System.out.println("\n🎬 영화 목록");
        for (MovieVO m : movies) {
            System.out.printf("🎞️ [%d] %s (%s, %s)\n", m.getMovieId(), m.getTitle(), m.getGenre(), m.getReleaseDate());
        }
    }

    private void reserveSeat() {
        System.out.print("\n🎞️ 영화 ID 입력: ");
        int movieId = Integer.parseInt(sc.nextLine());

        MovieVO movie = movieService.getMovieById(movieId);
        if (movie == null) {
            System.out.println("❌ 존재하지 않는 영화입니다.");
            return;
        }

        printSeats(movieId);

        System.out.print("🪑 예약할 좌석 (행 열): ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        sc.nextLine(); // 개행 제거

        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            System.out.println("❌ 유효하지 않은 좌석입니다.");
            return;
        }

        if (seatMap[movieId][row][col] == 1) {
            System.out.println("❌ 이미 예매된 좌석입니다.");
            return;
        }

        seatMap[movieId][row][col] = 1;
        System.out.printf("✅ [%s] %d행 %d열 예매 완료!\n", movie.getTitle(), row, col);
    }

    private void cancelSeat() {
        System.out.print("\n🎞️ 영화 ID 입력: ");
        int movieId = Integer.parseInt(sc.nextLine());

        MovieVO movie = movieService.getMovieById(movieId);
        if (movie == null) {
            System.out.println("❌ 존재하지 않는 영화입니다.");
            return;
        }

        printSeats(movieId);

        System.out.print("❌ 취소할 좌석 (행 열): ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        sc.nextLine(); // 개행 제거

        if (seatMap[movieId][row][col] == 0) {
            System.out.println("❌ 해당 좌석은 예매되어 있지 않습니다.");
            return;
        }

        seatMap[movieId][row][col] = 0;
        System.out.printf("✅ [%s] %d행 %d열 예매 취소 완료!\n", movie.getTitle(), row, col);
    }

    private void printSeats(int movieId) {
        System.out.println("\n🪑 좌석 현황 (0: 비어있음, 1: 예매됨)");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print("[" + seatMap[movieId][i][j] + "] ");
            }
            System.out.println();
        }
    }
    
    private void viewSeatStatus() {
        System.out.print("\n🎞️ 좌석 현황을 조회할 영화 ID 입력: ");
        int movieId = Integer.parseInt(sc.nextLine());

        MovieVO movie = movieService.getMovieById(movieId);
        if (movie == null) {
            System.out.println("❌ 존재하지 않는 영화입니다.");
            return;
        }

        System.out.printf("\n🎬 [%s] 좌석 현황:\n", movie.getTitle());
        printSeats(movieId);
    }

}
