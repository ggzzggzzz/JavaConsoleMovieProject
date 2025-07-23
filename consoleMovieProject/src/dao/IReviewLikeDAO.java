package dao;

import vo.ReviewLikeVO;

public interface IReviewLikeDAO {
    /**
     * 사용자가 특정 리뷰에 좋아요를 눌렀는지 확인
     * @param reviewId 리뷰 ID
     * @param memberId 회원 ID
     * @return 좋아요를 눌렀으면 true, 아니면 false
     */
    boolean checkLike(int reviewId, String memberId);

    /**
     * 리뷰에 좋아요 추가
     * @param vo ReviewLikeVO 객체 (reviewId, memberId)
     */
    void addLike(ReviewLikeVO vo);

    /**
     * 리뷰에 좋아요 삭제
     * @param vo ReviewLikeVO 객체 (reviewId, memberId)
     */
    void removeLike(ReviewLikeVO vo);
}
