package ui;

import java.util.List;
import service.IReviewService;
import service.ReviewService;
import vo.MemberVO;
import vo.ReviewVO;

public class ReviewUI extends BaseUI {

    private IReviewService reviewService = ReviewService.getInstance();
    private MemberVO loginUser;

    public ReviewUI(MemberVO loginUser) {
        this.loginUser = loginUser;
    }

    @Override
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
            System.out.println("  [5] 👍 리뷰 좋아요 토글");
            System.out.println("  [0] ↩️ 뒤로가기");
            System.out.println();
            
            int choice = getInt("👉 선택 > ");

            switch (choice) {
                case 1: writeReview(); break;
                case 2: viewMyReviews(); break;
                case 3: updateReview(); break;
                case 4: deleteReview(); break;
                case 5: toggleLike(); break;
                case 0:
                    System.out.println("🔙 리뷰 메뉴를 종료합니다.");
                    return;
                default:
                    System.out.println("⚠️ 잘못된 입력입니다.");
            }
        }
    }

    private void toggleLike() {
        System.out.println("\n👍👎 리뷰 좋아요 토글");
        int id = getInt("🆔 토글할 리뷰 ID: ");
        boolean isLiked = reviewService.toggleReviewLike(id, loginUser.getMemberId());
        if (isLiked) {
            System.out.println("✅ 좋아요 처리 완료!");
        } else {
            System.out.println("✅ 좋아요 취소 완료!");
        }
    }

    private void writeReview() {
        System.out.println("\n✍️ 리뷰 작성");
        int movieId = getInt("🎬 영화 ID 입력: ");
        
        System.out.print("⭐ 평점 (0~10): ");
        double rating = Double.parseDouble(sc.nextLine());

        String content = getString("📝 리뷰 내용: ");

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
            System.out.printf("🆔 %d | 🎬 영화 ID: %d | ⭐ 평점: %.1f | 👍 좋아요: %d\n📝 내용: %s\n",
                    r.getReviewId(), r.getMovieId(), r.getRating(), r.getLikeCount(), r.getContent());
            System.out.println("────────────────────────────────────────");
        }
    }

    private void updateReview() {
        System.out.println("\n✏️ 리뷰 수정");
        int id = getInt("🆔 수정할 리뷰 ID: ");
        
        System.out.print("⭐ 새 평점: ");
        double rating = Double.parseDouble(sc.nextLine());
        
        String content = getString("📝 새 내용: ");

        ReviewVO vo = new ReviewVO();
        vo.setReviewId(id);
        vo.setRating(rating);
        vo.setContent(content);

        reviewService.updateReview(vo, loginUser.getMemberId());
        System.out.println("✅ 리뷰 수정 완료!");
    }

    private void deleteReview() {
        System.out.println("\n❌ 리뷰 삭제");
        int id = getInt("🆔 삭제할 리뷰 ID: ");
        reviewService.deleteReview(id, loginUser.getMemberId());
        System.out.println("🗑️ 리뷰 삭제 완료!");
    }
}
