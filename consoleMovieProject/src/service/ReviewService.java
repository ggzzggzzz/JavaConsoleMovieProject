package service;

import java.util.List;
import dao.IReviewDAO;
import dao.IReviewLikeDAO;
import dao.ReviewDAO;
import dao.ReviewLikeDAO;
import vo.ReviewLikeVO;
import vo.ReviewVO;

public class ReviewService implements IReviewService {

    private static final ReviewService instance = new ReviewService();
    private IReviewDAO reviewDao;
    private IReviewLikeDAO reviewLikeDao;

    private ReviewService() {
        reviewDao = ReviewDAO.getInstance();
        reviewLikeDao = ReviewLikeDAO.getInstance();
    }

    public static ReviewService getInstance() {
        return instance;
    }

    @Override
    public void addReview(ReviewVO review) {
        reviewDao.insert(review);
    }

    @Override
    public void updateReview(ReviewVO review, String memberId) {
        reviewDao.update(review, memberId);
    }

    @Override
    public void deleteReview(int reviewId, String memberId) {
        reviewDao.delete(reviewId, memberId);
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
    public boolean toggleReviewLike(int reviewId, String memberId) {
        ReviewLikeVO vo = new ReviewLikeVO(reviewId, memberId);
        if (reviewLikeDao.checkLike(reviewId, memberId)) {
            // 이미 좋아요를 눌렀다면 -> 좋아요 취소
            reviewLikeDao.removeLike(vo);
            reviewDao.decreaseLike(reviewId);
            return false; // 좋아요 취소됨
        } else {
            // 좋아요를 누르지 않았다면 -> 좋아요 추가
            reviewLikeDao.addLike(vo);
            reviewDao.increaseLike(reviewId);
            return true; // 좋아요 추가됨
        }
    }

    @Override
    public double getAverageRating(int movieId) {
        return reviewDao.getAverageRating(movieId);
    }
}
