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
    public boolean toggleWishlist(WishlistVO wishlist) {
        // 이미 찜한 영화인지 확인
        if (wishlistDao.isWishlisted(wishlist.getMemberId(), wishlist.getMovieId())) {
            // 찜 되어 있으면 삭제
            wishlistDao.delete(wishlist.getMemberId(), wishlist.getMovieId());
            return false; // 찜 취소됨
        } else {
            // 찜 안되어 있으면 추가
            wishlistDao.insert(wishlist);
            return true; // 찜 추가됨
        }
    }

    @Override
    public List<WishlistVO> getWishlistByMember(String memberId) {
        return wishlistDao.selectByMember(memberId);
    }

    @Override
    public boolean isMovieWishlisted(String memberId, int movieId) {
        return wishlistDao.isWishlisted(memberId, movieId);
    }

    @Override
    public int getWishlistCountForMovie(int movieId) {
        return wishlistDao.countWishesByMovieId(movieId);
    }
}
