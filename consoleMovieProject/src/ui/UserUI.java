package ui;

import java.util.Scanner;

import vo.MemberVO;

public class UserUI {

    private MemberVO loginUser;
    private Scanner sc = new Scanner(System.in);

    public UserUI(MemberVO loginUser) {
        this.loginUser = loginUser;
    }//

    public void start() {
        while (true) {
            System.out.println("\n[사용자 메뉴]");
            System.out.println("1. 영화 목록 보기");
            System.out.println("2. 내 리뷰 관리");
            System.out.println("3. 찜 메뉴");
            System.out.println("4. 마이페이지");
            System.out.println("0. 로그아웃");
            System.out.print("메뉴 선택 : ");
            int choice = Integer.parseInt(sc.nextLine());

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
                case 0:
                    System.out.println("로그아웃되었습니다.\n");
                    return;
                default:
                    System.out.println("잘못된 번호입니다.");
            }
        }
    }
}
