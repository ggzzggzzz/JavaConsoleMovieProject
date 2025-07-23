package ui;

import java.util.List;
import service.IMovieService;
import service.IReviewService;
import service.MovieService;
import service.ReviewService;
import vo.MemberVO;
import vo.MovieVO;
import vo.ReviewVO;

public class MovieListUI extends BaseUI {

    private IMovieService movieService = MovieService.getInstance();
    private IReviewService reviewService = ReviewService.getInstance();
    private MemberVO loginUser;

    public MovieListUI(MemberVO loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public void start() {
        List<MovieVO> list = movieService.getAllMovies();

        System.out.println();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║             🎬 영화 목록 조회             ║");
        System.out.println("╚════════════════════════════════════════╝");

        if (list == null || list.isEmpty()) {
            System.out.println("📭 등록된 영화가 없습니다.");
            return;
        }

        System.out.println();
        System.out.println("🎞️ 현재 등록된 영화 리스트");
        System.out.println("────────────────────────────────────────");
        for (MovieVO movie : list) {
            double avgRating = reviewService.getAverageRating(movie.getMovieId());
            System.out.printf("🎬 [%d] %s | 장르: %s | ⭐ 평점: %.1f\n", 
                movie.getMovieId(), movie.getTitle(), movie.getGenre(), avgRating);
        }
        System.out.println("────────────────────────────────────────");

        int id = getInt("\n🔍 상세 정보를 볼 영화 ID 입력 (0: 취소): ");

        if (id != 0) {
            MovieVO movie = movieService.getMovieById(id);
            if (movie == null) {
                System.out.println("❌ 해당 ID의 영화를 찾을 수 없습니다.");
                return;
            }

            System.out.println();
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║         🎞️ 영화 상세 정보         ║");
            System.out.println("╚═══════════════");
            System.out.println("🎬 제목       : " + movie.getTitle());
            System.out.println("🎬 감독       : " + movie.getDirector());
            System.out.println("🎬 장르       : " + movie.getGenre());
            System.out.println("🎬 상영 시간  : " + movie.getRunningTime() + "분");
            System.out.println("📝 줄거리     : " + movie.getSynopsis());
            
            System.out.println("\n\n╔══════════════════════════════╗");
            System.out.println("║           📝 리뷰 목록           ║");
            System.out.println("╚══════════════════════════════╝");
            
            List<ReviewVO> reviews = reviewService.getReviewsByMovie(id);
            if (reviews.isEmpty()) {
                System.out.println("🕳️ 작성된 리뷰가 없습니다.");
            } else {
                for (ReviewVO review : reviews) {
                    System.out.println("────────────────────────────────────────");
                    System.out.printf("🆔 %d | 작성자: %s | ⭐ 평점: %.1f | 👍 좋아요: %d\n",
                            review.getReviewId(), review.getMemberId(), review.getRating(), review.getLikeCount());
                    System.out.println("📝 내용: " + review.getContent());
                }
                System.out.println("────────────────────────────────────────");
            }

            System.out.println("\n[1] 👍 리뷰 좋아요 토글  [0] ↩️ 뒤로가기");
            int choice = getInt("👉 선택 > ");
            if (choice == 1) {
                int reviewId = getInt("🆔 좋아요를 누를 리뷰 ID: ");
                boolean isLiked = reviewService.toggleReviewLike(reviewId, loginUser.getMemberId());
                if(isLiked) {
                	System.out.println("✅ 좋아요 처리 완료!");
                } else {
                	System.out.println("✅ 좋아요 취소 완료!");
                }
            }

        } else {
            System.out.println("↩️ 메인 메뉴로 돌아갑니다.");
        }
    }
}


