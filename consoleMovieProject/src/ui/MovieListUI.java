package ui;

import java.util.List;
import service.IMovieService;
import service.MovieService;
import vo.MovieVO;

public class MovieListUI extends BaseUI {

    private IMovieService movieService = MovieService.getInstance();

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
            System.out.printf("🎬 [%d] %s | 장르: %s\n", movie.getMovieId(), movie.getTitle(), movie.getGenre());
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
            System.out.println("╚══════════════════════════════╝");
            System.out.println("🎬 제목       : " + movie.getTitle());
            System.out.println("🎬 감독       : " + movie.getDirector());
            System.out.println("🎬 장르       : " + movie.getGenre());
            System.out.println("🎬 상영 시간  : " + movie.getRunningTime() + "분");
            System.out.println("📝 줄거리     : " + movie.getSynopsis());
        } else {
            System.out.println("↩️ 메인 메뉴로 돌아갑니다.");
        }
    }
}