package ui;

import java.util.Scanner;
import service.MemberService;
import vo.MemberVO;

public class SignupUI {

    private MemberService memberService = MemberService.getInstance();
    private Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println();
        System.out.println("╔════════════════════════════╗");
        System.out.println("║       📝 회원가입 페이지        ║");
        System.out.println("╚════════════════════════════╝");

        System.out.print("🆔 아이디 입력     : ");
        String id = sc.nextLine();

        System.out.print("🔑 비밀번호 입력   : ");
        String pw = sc.nextLine();

        System.out.print("🧑‍💻 닉네임 입력     : ");
        String nickname = sc.nextLine();

        MemberVO vo = new MemberVO(id, pw, nickname, "N", null);
        memberService.signup(vo);

        System.out.println("\n🎉 회원가입이 완료되었습니다! 로그인 해주세요.");
    }
}
