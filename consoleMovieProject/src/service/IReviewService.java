package service;

import java.util.List;
import vo.ReviewVO;

public interface IReviewService {
    void addReview(ReviewVO review);
    void updateReview(ReviewVO review, String memberId);
    void deleteReview(int reviewId, String memberId);
    List<ReviewVO> getReviewsByMovie(int movieId);
    List<ReviewVO> getReviewsByMember(String memberId);
    boolean toggleReviewLike(int reviewId, String memberId); // 좋아요 토글 기능
    double getAverageRating(int movieId);
}
