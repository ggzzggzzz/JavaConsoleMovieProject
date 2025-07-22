package dao;

import java.util.List;
import vo.MemberVO;

public interface IMemberDAO {
    void signup(MemberVO vo);
    MemberVO login(String id, String pw);
    List<MemberVO> selectAll();
    void updateNickname(String memberId, String newNickname);
    void updatePassword(String memberId, String newPassword);
    boolean checkPassword(String memberId, String currentPassword);
    void deleteMember(String memberId);
    MemberVO selectById(String memberId);
}
