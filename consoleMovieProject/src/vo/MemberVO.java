package vo;

public class MemberVO {
    private String memberId;
    private String password;
    private String nickname;
    private String isAdmin; 
    private String createdAt; 
    
    ///////
    public MemberVO() {}

    public MemberVO(String memberId, String password, String nickname, String isAdmin, String createdAt) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.isAdmin = isAdmin;
        this.createdAt = createdAt;
    }
//asdasd
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
