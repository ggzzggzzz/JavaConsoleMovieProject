package service;

import java.util.List;

import dao.MemberDAO;
import vo.MemberVO;

public class MemberService {

    private MemberDAO memberDao;

    public MemberService() {
        memberDao = new MemberDAO();
    }

    public void signup(MemberVO vo) {
        memberDao.signup(vo);
    }

    public MemberVO login(String id, String pw) {
        return memberDao.login(id, pw);
    }

    public List<MemberVO> getAllMembers() {
        return memberDao.selectAll();
    }

    public void updateNickname(String memberId, String newNickname) {
        memberDao.updateNickname(memberId, newNickname);
    }

    public void updatePassword(String memberId, String newPassword) {
        memberDao.updatePassword(memberId, newPassword);
    }

    public void deleteMember(String memberId) {
        memberDao.deleteMember(memberId);
    }

    public MemberVO getMemberById(String memberId) {
        return memberDao.selectById(memberId);
    }
}
