package ui;

import java.util.Scanner;

import service.MemberService;
import vo.MemberVO;

public class LoginUI {

    private MemberService memberService = MemberService.getInstance();
    private Scanner sc = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("========== 영화 리뷰 로그인 ==========");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택 : ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    signup();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 번호입니다.");
            }
        }
    }

    private void login() {
        System.out.print("아이디 입력: ");
        String id = sc.nextLine();
        System.out.print("비밀번호 입력: ");
        String pw = sc.nextLine();

        MemberVO loginUser = memberService.login(id, pw);
        
        if (loginUser == null) {
            System.out.println("로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
            return;
        }

        System.out.println(loginUser.getNickname() + "님 환영합니다!");
        
        if (loginUser.getIsAdmin().equals("Y")) {
            new AdminUI(loginUser).start();
        } else {
            new UserUI(loginUser).start();
        }
    }

    private void signup() {
        new SignupUI().start();
    }
}
