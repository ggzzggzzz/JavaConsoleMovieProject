package dao;

import java.util.List;
import vo.ReviewVO;

public interface IReviewDAO {
    void insert(ReviewVO vo);
    void update(ReviewVO vo, String memberId);
    void delete(int reviewId, String memberId);
    List<ReviewVO> selectByMovieId(int movieId);
    List<ReviewVO> selectByMemberId(String memberId);
    void increaseLike(int reviewId);
    void decreaseLike(int reviewId); // 좋아요 취소 메소드 추가
    double getAverageRating(int movieId);
}
