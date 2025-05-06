package ui;

import java.util.Scanner;
import vo.MemberVO;

public class UserUI {

    private MemberVO loginUser;
    private Scanner sc = new Scanner(System.in);

    public UserUI(MemberVO loginUser) {
        this.loginUser = loginUser;
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("╔════════════════════════════╗");
            System.out.printf("║  🙋‍♂️ %s님의 사용자 메뉴  ║\n", loginUser.getNickname());
            System.out.println("╚════════════════════════════╝");
            System.out.println();
            System.out.println("  [1] 🎬 영화 목록 보기");
            System.out.println("  [2] 📝 내 리뷰 관리");
            System.out.println("  [3] 💖 찜 목록");
            System.out.println("  [4] 🧑‍💻 마이페이지");
            System.out.println("  [5] 🎟️ 예매 서비스");
            System.out.println("  [0] 🔓 로그아웃");
            System.out.println();
            System.out.print("👉 메뉴 선택 : ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ 숫자를 입력해주세요.");
                continue;
            }

            switch (choice) {
                case 1:
                    new MovieListUI().start();
                    break;
                case 2:
                    new ReviewUI(loginUser).start();
                    break;
                case 3:
                    new WishlistUI(loginUser).start();
                    break;
                case 4:
                    new MyPageUI(loginUser).start();
                    break;
                case 5:
                	new ReservationUI(loginUser).start();
                    break;
                case 0:
                    System.out.println("\n👋 로그아웃되었습니다.");
                    return;
                default:
                    System.out.println("⚠️ 잘못된 번호입니다.");
            }
        }
    }
}
