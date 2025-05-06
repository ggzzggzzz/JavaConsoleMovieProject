package service;

import java.util.List;

import dao.MovieDAO;
import vo.MovieVO;

public class MovieService {

    private MovieDAO movieDao;

    public MovieService() {
        movieDao = new MovieDAO();
    }

    public void addMovie(MovieVO movie) {
        movieDao.insert(movie);
    }

    public void deleteMovie(int movieId) {
        movieDao.delete(movieId);
    }

    public List<MovieVO> getAllMovies() {
        return movieDao.selectAll();
    }

    public MovieVO getMovieById(int movieId) {
        return movieDao.selectById(movieId);
    }

    public List<MovieVO> searchMoviesByTitle(String keyword) {
        return movieDao.searchByTitle(keyword);
    }

    public List<MovieVO> filterMoviesByGenre(String genre) {
        return movieDao.serchByGenre(genre);
    }
}
