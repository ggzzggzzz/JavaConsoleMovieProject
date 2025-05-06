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
            System.out.println("\n[리뷰 메뉴]");
            System.out.println("1. 리뷰 작성");
            System.out.println("2. 내가 쓴 리뷰 보기");
            System.out.println("3. 리뷰 수정");
            System.out.println("4. 리뷰 삭제");
            System.out.println("0. 뒤로가기");
            System.out.print("선택 > ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    writeReview();
                    break;
                case 2:
                    viewMyReviews();
                    break;
                case 3:
                    updateReview();
                    break;
                case 4:
                    deleteReview();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void writeReview() {
        System.out.print("영화 ID: ");
        int movieId = Integer.parseInt(sc.nextLine());
        System.out.print("평점 (0~10): ");
        double rating = Double.parseDouble(sc.nextLine());
        System.out.print("리뷰 내용: ");
        String content = sc.nextLine();

        int reviewId = (int) (Math.random() * 1000000); // 예시용, 실제는 시퀀스 사용
        ReviewVO vo = new ReviewVO(reviewId, loginUser.getMemberId(), movieId, rating, content, 0, null);
        reviewService.addReview(vo);

        System.out.println("리뷰 작성 완료!");
    }

    private void viewMyReviews() {
        List<ReviewVO> list = reviewService.getReviewsByMember(loginUser.getMemberId());
        for (ReviewVO r : list) {
            System.out.printf("ID: %d | 영화: %s | 평점: %.1f | 내용: %s\n", r.getReviewId(), r.getMovieId(), r.getRating(), r.getContent());
        }
    }

    private void updateReview() {
        System.out.print("수정할 리뷰 ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("새 평점: ");
        double rating = Double.parseDouble(sc.nextLine());
        System.out.print("새 내용: ");
        String content = sc.nextLine();

        ReviewVO vo = new ReviewVO();
        vo.setReviewId(id);
        vo.setRating(rating);
        vo.setContent(content);

        reviewService.updateReview(vo);
        System.out.println("리뷰 수정 완료!");
    }

    private void deleteReview() {
        System.out.print("삭제할 리뷰 ID: ");
        int id = Integer.parseInt(sc.nextLine());
        reviewService.deleteReview(id);
        System.out.println("리뷰 삭제 완료!");
    }
}
