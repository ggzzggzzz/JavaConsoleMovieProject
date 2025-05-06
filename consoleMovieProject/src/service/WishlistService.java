package service;

import java.util.List;

import dao.WishlistDAO;
import vo.WishlistVO;

public class WishlistService {

    private WishlistDAO wishlistDao;

    public WishlistService() {
        wishlistDao = new WishlistDAO();
    }

    public void addWishlist(WishlistVO wishlist) {
        wishlistDao.insert(wishlist);
    }

    public void removeWishlist(String memberId, int movieId) {
        wishlistDao.delete(memberId, movieId);
    }

    public List<WishlistVO> getWishlistByMember(String memberId) {
        return wishlistDao.selectByMember(memberId);
    }

    public boolean isMovieWishlisted(String memberId, int movieId) {
        return wishlistDao.isWishlisted(memberId, movieId);
    }
}
