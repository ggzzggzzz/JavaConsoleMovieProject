package ui;

import java.util.List;

import service.IMemberService;
import service.MemberService;
import vo.MemberVO;

public class MemberListUI {

    public void start() {
        System.out.println("\n[회원 목록 조회]");
        List<MemberVO> members = MemberService.getInstance().getAllMembers();


        if (members.isEmpty()) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }

        for (MemberVO member : members) {
            System.out.printf("아이디: %s | 닉네임: %s |",
                member.getMemberId(), member.getNickname());
        }
    }
}
