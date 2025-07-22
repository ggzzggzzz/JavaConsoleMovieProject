package service;

import java.util.List;
import dao.IWishlistDAO;
import dao.WishlistDAO;
import vo.WishlistVO;

public class WishlistService implements IWishlistService {

    private static final WishlistService instance = new WishlistService();
    private IWishlistDAO wishlistDao;

    private WishlistService() {
        wishlistDao = WishlistDAO.getInstance();
    }

    public static WishlistService getInstance() {
        return instance;
    }

    @Override
    public void addWishlist(WishlistVO wishlist) {
        wishlistDao.insert(wishlist);
    }

    @Override
    public void removeWishlist(String memberId, int movieId) {
        wishlistDao.delete(memberId, movieId);
    }

    @Override
    public List<WishlistVO> getWishlistByMember(String memberId) {
        return wishlistDao.selectByMember(memberId);
    }

    @Override
    public boolean isMovieWishlisted(String memberId, int movieId) {
        return wishlistDao.isWishlisted(memberId, movieId);
    }
}
