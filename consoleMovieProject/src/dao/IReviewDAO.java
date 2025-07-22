package dao;

import java.util.List;
import vo.ReviewVO;

public interface IReviewDAO {
    void insert(ReviewVO vo);
    void update(ReviewVO vo);
    void delete(int reviewId);
    List<ReviewVO> selectByMovieId(int movieId);
    List<ReviewVO> selectByMemberId(String memberId);
    void increaseLike(int reviewId);
    double getAverageRating(int movieId);
}
