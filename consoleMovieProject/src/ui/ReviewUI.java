package ui;

import java.util.List;
import java.util.Scanner;
import service.ReviewService;
import vo.MemberVO;
import vo.ReviewVO;

public class ReviewUI {

    private ReviewService reviewService = ReviewService.getInstance();
    private MemberVO loginUser;
    private Scanner sc = new Scanner(System.in);

    public ReviewUI(MemberVO loginUser) {
        this.loginUser = loginUser;
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("╔════════════════════════════╗");
            System.out.println("║         📝 리뷰 메뉴         ║");
            System.out.println("╚════════════════════════════╝");
            System.out.println();
            System.out.println("  [1] ✍️ 리뷰 작성");
            System.out.println("  [2] 📄 내가 쓴 리뷰 보기");
            System.out.println("  [3] ✏️ 리뷰 수정");
            System.out.println("  [4] ❌ 리뷰 삭제");
            System.out.println("  [0] ↩️ 뒤로가기");
            System.out.println();
            System.out.print("👉 선택 > ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ 숫자를 입력해주세요.");
                continue;
            }

            switch (choice) {
                case 1: writeReview(); break;
                case 2: viewMyReviews(); break;
                case 3: updateReview(); break;
                case 4: deleteReview(); break;
                case 0:
                    System.out.println("🔙 리뷰 메뉴를 종료합니다.");
                    return;
                default:
                    System.out.println("⚠️ 잘못된 입력입니다.");
            }
        }
    }

    private void writeReview() {
        System.out.println("\n✍️ 리뷰 작성");
        System.out.print("🎬 영화 ID 입력: ");
        int movieId = Integer.parseInt(sc.nextLine());
        System.out.print("⭐ 평점 (0~10): ");
        double rating = Double.parseDouble(sc.nextLine());
        System.out.print("📝 리뷰 내용: ");
        String content = sc.nextLine();

        ReviewVO vo = new ReviewVO(1, loginUser.getMemberId(), movieId, rating, content, 0, null);
        reviewService.addReview(vo);

        System.out.println("✅ 리뷰 작성 완료!");
    }

    private void viewMyReviews() {
        System.out.println("\n📄 내가 쓴 리뷰 목록");
        System.out.println("────────────────────────────────────────");
        List<ReviewVO> list = reviewService.getReviewsByMember(loginUser.getMemberId());

        if (list.isEmpty()) {
            System.out.println("🕳️ 작성한 리뷰가 없습니다.");
            return;
        }

        for (ReviewVO r : list) {
            System.out.printf("🆔 %d | 🎬 영화: %s | ⭐ 평점: %.1f\n📝 내용: %s\n",
                    r.getReviewId(), r.getMovieId(), r.getRating(), r.getContent());
            System.out.println("────────────────────────────────────────");
        }
    }

    private void updateReview() {
        System.out.println("\n✏️ 리뷰 수정");
        System.out.print("🆔 수정할 리뷰 ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("⭐ 새 평점: ");
        double rating = Double.parseDouble(sc.nextLine());
        System.out.print("📝 새 내용: ");
        String content = sc.nextLine();

        ReviewVO vo = new ReviewVO();
        vo.setReviewId(id);
        vo.setRating(rating);
        vo.setContent(content);

        reviewService.updateReview(vo);
        System.out.println("✅ 리뷰 수정 완료!");
    }

    private void deleteReview() {
        System.out.println("\n❌ 리뷰 삭제");
        System.out.print("🆔 삭제할 리뷰 ID: ");
        int id = Integer.parseInt(sc.nextLine());
        reviewService.deleteReview(id);
        System.out.println("🗑️ 리뷰 삭제 완료!");
    }
}
