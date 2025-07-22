package ui;

import service.IMemberService;
import service.MemberService;
import vo.MemberVO;

public class MyPageUI extends BaseUI {

    private IMemberService memberService = MemberService.getInstance();
    private MemberVO loginUser;

    public MyPageUI(MemberVO loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public void start() {
        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════╗");
            System.out.printf("║   🧑‍💼 %s님의 마이페이지   ║\n", loginUser.getNickname());
            System.out.println("╚══════════════════════════════╝");
            System.out.println();
            System.out.println("  [1] ✏️ 닉네임 변경");
            System.out.println("  [2] 🔑 비밀번호 변경");
            System.out.println("  [3] ❌ 회원 탈퇴");
            System.out.println("  [0] ↩️ 뒤로가기");
            System.out.println();
            
            int choice = getInt("👉 선택 > ");

            switch (choice) {
                case 1:
                    String nickname = getString("✏️ 새 닉네임 입력: ");
                    memberService.updateNickname(loginUser.getMemberId(), nickname);
                    loginUser.setNickname(nickname);
                    System.out.println("✅ 닉네임 변경 완료!");
                    break;

                case 2:
                    String currentPw = getString("🔒 현재 비밀번호 입력: ");

                    if (!memberService.isCurrentPasswordCorrect(loginUser.getMemberId(), currentPw)) {
                        System.out.println("❌ 현재 비밀번호가 일치하지 않습니다.");
                        break;
                    }

                    String newPw = getString("🆕 새 비밀번호 입력: ");
                    memberService.updatePassword(loginUser.getMemberId(), newPw);
                    System.out.println("✅ 비밀번호 변경 완료!");
                    break;

                case 3:
                    String confirm = getString("⚠️ 정말 탈퇴하시겠습니까? (y/n): ");
                    if (confirm.equalsIgnoreCase("y")) {
                        memberService.deleteMember(loginUser.getMemberId());
                        System.out.println("👋 회원 탈퇴 완료. 프로그램을 종료합니다.");
                        System.exit(0);
                    } else {
                        System.out.println("↩️ 탈퇴가 취소되었습니다.");
                    }
                    break;

                case 0:
                    System.out.println("🔙 마이페이지를 종료합니다.");
                    return;

                default:
                    System.out.println("⚠️ 잘못된 입력입니다.");
            }
        }
    }
}
