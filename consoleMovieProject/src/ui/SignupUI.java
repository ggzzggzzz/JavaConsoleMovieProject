package ui;

import service.IMemberService;
import service.MemberService;
import vo.MemberVO;

public class SignupUI extends BaseUI {

    private IMemberService memberService = MemberService.getInstance();

    @Override
    public void start() {
        System.out.println();
        System.out.println("╔════════════════════════════╗");
        System.out.println("║       📝 회원가입 페이지        ║");
        System.out.println("╚════════════════════════════╝");

        String id = getString("🆔 아이디 입력     : ");
        String pw = getString("🔑 비밀번호 입력   : ");
        String nickname = getString("🧑‍💻 닉네임 입력     : ");

        MemberVO vo = new MemberVO(id, pw, nickname, "N", null);
        memberService.signup(vo);

        System.out.println("\n🎉 회원가입이 완료되었습니다! 로그인 해주세요.");
    }
}
