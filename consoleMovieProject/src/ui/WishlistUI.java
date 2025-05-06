package ui;

import java.util.List;
import java.util.Scanner;

import service.WishlistService;
import vo.MemberVO;
import vo.WishlistVO;

public class WishlistUI {

    private WishlistService wishlistService = WishlistService.getInstance();
    private MemberVO loginUser;
    private Scanner sc = new Scanner(System.in);

    public WishlistUI(MemberVO loginUser) {
        this.loginUser = loginUser;
    }

    public void start() {
        while (true) {
            System.out.println("\n[찜 메뉴]");
            System.out.println("1. 찜한 영화 목록 보기");
            System.out.println("2. 영화 찜 추가");
            System.out.println("3. 찜 삭제");
            System.out.println("0. 뒤로가기");
            System.out.print("선택 > ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    viewWishlist();
                    break;
                case 2:
                    addWishlist();
                    break;
                case 3:
                    deleteWishlist();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void viewWishlist() {
        List<WishlistVO> list = wishlistService.getWishlistByMember(loginUser.getMemberId());
        for (WishlistVO w : list) {
            System.out.printf("ID: %d | 영화 ID: %d | 추가일: %s\n", w.getWishlistId(), w.getMovieId(), w.getCreatedAt());
        }
    }

    private void addWishlist() {
        System.out.print("영화 ID: ");
        int movieId = Integer.parseInt(sc.nextLine());

        boolean already = wishlistService.isMovieWishlisted(loginUser.getMemberId(), movieId);
        if (already) {
            System.out.println("이미 찜한 영화입니다.");
            return;
        }

        int wishlistId = (int) (Math.random() * 1000000); // 예시용
        WishlistVO vo = new WishlistVO(wishlistId, loginUser.getMemberId(), movieId, null);
        wishlistService.addWishlist(vo);

        System.out.println("찜 완료!");
    }

    private void deleteWishlist() {
        System.out.print("삭제할 영화 ID: ");
        int movieId = Integer.parseInt(sc.nextLine());
        wishlistService.removeWishlist(loginUser.getMemberId(), movieId);
        System.out.println("찜 삭제 완료!");
    }
}
