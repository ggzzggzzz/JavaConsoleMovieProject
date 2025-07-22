package service;

import java.util.List;
import vo.MemberVO;

public interface IMemberService {
    void signup(MemberVO vo);
    MemberVO login(String id, String pw);
    List<MemberVO> getAllMembers();
    void updateNickname(String memberId, String newNickname);
    void updatePassword(String memberId, String newPassword);
    boolean isCurrentPasswordCorrect(String memberId, String currentPassword);
    void deleteMember(String memberId);
    MemberVO getMemberById(String memberId);
}
