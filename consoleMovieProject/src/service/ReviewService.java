package service;

import java.util.List;
import dao.IReviewDAO;
import dao.ReviewDAO;
import vo.ReviewVO;

public class ReviewService implements IReviewService {

    private static final ReviewService instance = new ReviewService();
    private IReviewDAO reviewDao;

    private ReviewService() {
        reviewDao = ReviewDAO.getInstance();
    }

    public static ReviewService getInstance() {
        return instance;
    }

    @Override
    public void addReview(ReviewVO review) {
        reviewDao.insert(review);
    }

    @Override
    public void updateReview(ReviewVO review) {
        reviewDao.update(review);
    }

    @Override
    public void deleteReview(int reviewId) {
        reviewDao.delete(reviewId);
    }

    @Override
    public List<ReviewVO> getReviewsByMovie(int movieId) {
        return reviewDao.selectByMovieId(movieId);
    }

    @Override
    public List<ReviewVO> getReviewsByMember(String memberId) {
        return reviewDao.selectByMemberId(memberId);
    }

    @Override
    public void likeReview(int reviewId) {
        reviewDao.increaseLike(reviewId);
    }

    @Override
    public double getAverageRating(int movieId) {
        return reviewDao.getAverageRating(movieId);
    }
}
