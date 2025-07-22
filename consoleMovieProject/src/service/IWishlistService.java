package service;

import java.util.List;
import vo.WishlistVO;

public interface IWishlistService {
    void addWishlist(WishlistVO wishlist);
    void removeWishlist(String memberId, int movieId);
    List<WishlistVO> getWishlistByMember(String memberId);
    boolean isMovieWishlisted(String memberId, int movieId);
}
