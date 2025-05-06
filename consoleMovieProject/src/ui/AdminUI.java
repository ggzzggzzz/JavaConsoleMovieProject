package ui;

import java.util.Scanner;

import vo.MemberVO;

public class AdminUI {

    private MemberVO loginUser;
    private Scanner sc = new Scanner(System.in);

    public AdminUI(MemberVO loginUser) {
        this.loginUser = loginUser;
    }

    public void start() {
        while (true) {
            System.out.println("\n[관리자 메뉴]");
            System.out.println("1. 영화 등록");
            System.out.println("2. 영화 삭제");
            System.out.println("3. 회원 목록 조회");
            System.out.println("0. 로그아웃");
            System.out.print("메뉴 선택 : ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    // new MovieRegisterUI().start(); 
                    break;
                case 2:
                    // new MovieDeleteUI().start(); 
                    break;
                case 3:
                    // new MemberListUI().start(); 
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
