package service;

import java.util.List;
import dao.ReviewDAO;
import vo.ReviewVO;

public class ReviewService {

    private static final ReviewService instance = new ReviewService();
    private ReviewDAO reviewDao;

    private ReviewService() {
        reviewDao = ReviewDAO.getInstance();
    }

    public static ReviewService getInstance() {
        return instance;
    }

    public void addReview(ReviewVO review) {
        reviewDao.insert(review);
    }

    public void updateReview(ReviewVO review) {
        reviewDao.update(review);
    }

    public void deleteReview(int reviewId) {
        reviewDao.delete(reviewId);
    }

    public List<ReviewVO> getReviewsByMovie(int movieId) {
        return reviewDao.selectByMovieId(movieId);
    }

    public List<ReviewVO> getReviewsByMember(String memberId) {
        return reviewDao.selectByMemberId(memberId);
    }

    public void likeReview(int reviewId) {
        reviewDao.increaseLike(reviewId);
    }

    public double getAverageRating(int movieId) {
        return reviewDao.getAverageRating(movieId);
    }
}
