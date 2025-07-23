package ui;

import java.util.List;
import service.IWishlistService;
import service.WishlistService;
import vo.MemberVO;
import vo.WishlistVO;

public class WishlistUI extends BaseUI {

    private IWishlistService wishlistService = WishlistService.getInstance();
    private MemberVO loginUser;

    public WishlistUI(MemberVO loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public void start() {
        while (true) {
            System.out.println();
            System.out.println("╔════════════════════════════╗");
            System.out.println("║         💖 찜 메뉴          ║");
            System.out.println("╚════════════════════════════╝");
            System.out.println();
            System.out.println("  [1] 📋 찜한 영화 목록 보기");
            System.out.println("  [2] 💖 영화 찜하기/취소");
            System.out.println("  [0] ↩️ 뒤로가기");
            System.out.println();
            
            int choice = getInt("👉 선택 > ");

            switch (choice) {
                case 1: viewWishlist(); break;
                case 2: toggleWishlist(); break;
                case 0:
                    System.out.println("🔙 찜 메뉴를 종료합니다.");
                    return;
                default:
                    System.out.println("⚠️ 잘못된 입력입니다.");
            }
        }
    }

    private void viewWishlist() {
        System.out.println("\n📋 내 찜 목록");
        System.out.println("────────────────────────────────────────");
        List<WishlistVO> list = wishlistService.getWishlistByMember(loginUser.getMemberId());

        if (list.isEmpty()) {
            System.out.println("🕳️ 찜한 영화가 없습니다.");
            return;
        }

        for (WishlistVO w : list) {
            System.out.printf("💖 찜 ID: %d | 🎬 영화 ID: %d | 📅 추가일: %s\n",
                    w.getWishlistId(), w.getMovieId(), w.getCreatedAt());
        }
        System.out.println("────────────────────────────────────────");
    }

    private void toggleWishlist() {
        System.out.println("\n💖 영화 찜하기/취소");
        int movieId = getInt("🎬 영화 ID 입력: ");

        WishlistVO vo = new WishlistVO(0, loginUser.getMemberId(), movieId, null);
        boolean added = wishlistService.toggleWishlist(vo);

        if (added) {
            System.out.println("✅ 찜 목록에 추가되었습니다!");
        } else {
            System.out.println("🗑️ 찜 목록에서 삭제되었습니다.");
        }
    }
}
