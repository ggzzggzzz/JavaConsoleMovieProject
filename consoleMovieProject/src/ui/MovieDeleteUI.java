package ui;

import service.IMovieService;
import service.MovieService;

public class MovieDeleteUI extends BaseUI {

    private IMovieService movieService = MovieService.getInstance();

    @Override
    public void start() {
        System.out.println("\n[영화 삭제]");
        int movieId = getInt("삭제할 영화 ID 입력: ");

        movieService.deleteMovie(movieId);
        System.out.println("✅ 영화가 삭제되었습니다.");
    }
}

