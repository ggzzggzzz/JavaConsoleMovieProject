package vo;

public class ReviewLikeVO {
    private int reviewId;
    private String memberId;
    private String createdAt;

    public ReviewLikeVO() {}

    public ReviewLikeVO(int reviewId, String memberId) {
        this.reviewId = reviewId;
        this.memberId = memberId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
