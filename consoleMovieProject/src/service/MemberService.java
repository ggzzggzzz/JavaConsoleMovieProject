package service;

import java.util.List;
import dao.IMemberDAO;
import dao.MemberDAO;
import vo.MemberVO;

public class MemberService implements IMemberService {

    private static final MemberService instance = new MemberService();
    private IMemberDAO memberDao;

    private MemberService() {
        memberDao = MemberDAO.getInstance();
    }

    public static MemberService getInstance() {
        return instance;
    }

    @Override
    public void signup(MemberVO vo) {
        memberDao.signup(vo);
    }

    @Override
    public MemberVO login(String id, String pw) {
        return memberDao.login(id, pw);
    }

    @Override
    public List<MemberVO> getAllMembers() {
        return memberDao.selectAll();
    }

    @Override
    public void updateNickname(String memberId, String newNickname) {
        memberDao.updateNickname(memberId, newNickname);
    }

    @Override
    public void updatePassword(String memberId, String newPassword) {
        memberDao.updatePassword(memberId, newPassword);
    }
    
    @Override
    public boolean isCurrentPasswordCorrect(String memberId, String currentPassword) {
       
        return memberDao.checkPassword(memberId, currentPassword);
    }

    @Override
    public void deleteMember(String memberId) {
        memberDao.deleteMember(memberId);
    }

    @Override
    public MemberVO getMemberById(String memberId) {
        return memberDao.selectById(memberId);
    }
}
