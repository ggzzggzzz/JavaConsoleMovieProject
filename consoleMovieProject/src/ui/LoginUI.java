package ui;

import service.IMemberService;
import service.MemberService;
import vo.MemberVO;

public class LoginUI extends BaseUI {

    private IMemberService memberService = MemberService.getInstance();

    @Override
    public void start() {
        while (true) {
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║         🎬 영화 리뷰 시스템 로그인         ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.println();
            System.out.println("  [1] 🔐 로그인");
            System.out.println("  [2] 📝 회원가입");
            System.out.println("  [0] ❌ 종료");
            System.out.println();
            
            int choice = getInt("👉 메뉴 선택 : ");

            switch (choice) {
                case 1: login(); break;
                case 2: signup(); break;
                case 0:
                    System.out.println("👋 프로그램을 종료합니다. 안녕히 가세요!");
                    return;
                default:
                    System.out.println("⚠️ 잘못된 번호입니다. 다시 입력해주세요.");
            }
        }
    }

    private void login() {
        System.out.println();
        System.out.println("========== 🔐 로그인 페이지 ==========");
        String id = getString("👤 아이디 입력: ");
        String pw = getString("🔑 비밀번호 입력: ");

        MemberVO loginUser = memberService.login(id, pw);

        if (loginUser == null) {
            System.out.println("❌ 로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
            return;
        }

        System.out.println("✅ " + loginUser.getNickname() + "님 환영합니다!");

        if ("Y".equals(loginUser.getIsAdmin())) {
            new AdminUI(loginUser).start();
        } else {
            new UserUI(loginUser).start();
        }
    }

    private void signup() {
        new SignupUI().start();
    }
}
