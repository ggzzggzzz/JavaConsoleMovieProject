package service;

import java.util.List;
import dao.WishlistDAO;
import vo.WishlistVO;

public class WishlistService {

    private static final WishlistService instance = new WishlistService();
    private WishlistDAO wishlistDao;

    private WishlistService() {
        wishlistDao = WishlistDAO.getInstance();
    }

    public static WishlistService getInstance() {
        return instance;
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
