package service;

import java.util.List;

import dao.ReviewDAO;
import vo.ReviewVO;

public class ReviewService {

    private ReviewDAO reviewDao;

    public ReviewService() {
        reviewDao = new ReviewDAO();
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
