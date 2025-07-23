package service;

import java.util.List;
import vo.WishlistVO;

public interface IWishlistService {
    // 찜 추가/삭제를 한 번에 처리하는 토글 메소드
    boolean toggleWishlist(WishlistVO wishlist); 
    
    List<WishlistVO> getWishlistByMember(String memberId);
    boolean isMovieWishlisted(String memberId, int movieId);
    
    // 영화별 찜 개수를 가져오는 메소드
    int getWishlistCountForMovie(int movieId); 
}
