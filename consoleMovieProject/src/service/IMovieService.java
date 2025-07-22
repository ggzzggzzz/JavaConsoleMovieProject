package service;

import java.util.List;
import vo.MovieVO;

public interface IMovieService {
    void addMovie(MovieVO movie);
    void deleteMovie(int movieId);
    List<MovieVO> getAllMovies();
    MovieVO getMovieById(int movieId);
    List<MovieVO> searchMoviesByTitle(String keyword);
    List<MovieVO> filterMoviesByGenre(String genre);
}
