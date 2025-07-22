package ui;

import service.IMovieService;
import service.MovieService;
import vo.MovieVO;

public class MovieRegisterUI extends BaseUI {

    private IMovieService movieService = MovieService.getInstance();

    @Override
    public void start() {
        System.out.println("\n[영화 등록]");

        String title = getString("제목: ");
        String genre = getString("장르: ");
        String director = getString("감독: ");
        int runningTime = getInt("상영 시간(분): ");
        String releaseDate = getString("개봉일(YYYY-MM-DD): ");
        String synopsis = getString("줄거리: ");

        MovieVO vo = new MovieVO();
        vo.setTitle(title);
        vo.setGenre(genre);
        vo.setDirector(director);
        vo.setRunningTime(runningTime);
        vo.setReleaseDate(releaseDate);
        vo.setSynopsis(synopsis);

        movieService.addMovie(vo);
        System.out.println("✅ 영화 등록 완료");
    }
}

