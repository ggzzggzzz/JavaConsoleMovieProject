package ui;

import java.util.Scanner;

import service.MemberService;
import vo.MemberVO;

public class MyPageUI {

    private MemberService memberService = MemberService.getInstance();
    private MemberVO loginUser;
    private Scanner sc = new Scanner(System.in);

    public MyPageUI(MemberVO loginUser) {
        this.loginUser = loginUser;
    }

    public void start() {
        while (true) {
            System.out.println("\n[마이페이지]");
            System.out.println("1. 닉네임 변경");
            System.out.println("2. 비밀번호 변경");
            System.out.println("3. 회원 탈퇴");
            System.out.println("0. 뒤로가기");
            System.out.print("선택 > ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("새 닉네임 입력: ");
                    String nickname = sc.nextLine();
                    memberService.updateNickname(loginUser.getMemberId(), nickname);
                    loginUser.setNickname(nickname);
                    System.out.println("닉네임 변경 완료!");
                    break;
                case 2:
                    System.out.print("새 비밀번호 입력: ");
                    String newPw = sc.nextLine();
                    memberService.updatePassword(loginUser.getMemberId(), newPw);
                    System.out.println("비밀번호 변경 완료!");
                    break;
                case 3:
                    System.out.print("정말 탈퇴하시겠습니까? (y/n): ");
                    String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("y")) {
                        memberService.deleteMember(loginUser.getMemberId());
                        System.out.println("회원 탈퇴 완료. 프로그램을 종료합니다.");
                        System.exit(0);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
