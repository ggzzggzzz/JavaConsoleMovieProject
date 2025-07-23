package dao;

import java.util.List;
import vo.WishlistVO;

public interface IWishlistDAO {
    void insert(WishlistVO vo);
    void delete(String memberId, int movieId);
    List<WishlistVO> selectByMember(String memberId);
    boolean isWishlisted(String memberId, int movieId);
    int countWishesByMovieId(int movieId); // 추가된 메소드
}
