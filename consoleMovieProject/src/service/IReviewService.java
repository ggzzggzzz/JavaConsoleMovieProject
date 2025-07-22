package service;

import java.util.List;
import vo.ReviewVO;

public interface IReviewService {
    void addReview(ReviewVO review);
    void updateReview(ReviewVO review);
    void deleteReview(int reviewId);
    List<ReviewVO> getReviewsByMovie(int movieId);
    List<ReviewVO> getReviewsByMember(String memberId);
    void likeReview(int reviewId);
    double getAverageRating(int movieId);
}
