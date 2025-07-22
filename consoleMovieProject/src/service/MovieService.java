package service;

import java.util.List;
import dao.IMovieDAO;
import dao.MovieDAO;
import vo.MovieVO;

public class MovieService implements IMovieService {

    private static final MovieService instance = new MovieService();
    private IMovieDAO movieDao;

    private MovieService() {
        movieDao = MovieDAO.getInstance();
    }

    public static MovieService getInstance() {
        return instance;
    }

    @Override
    public void addMovie(MovieVO movie) {
        movieDao.insert(movie);
    }

    @Override
    public void deleteMovie(int movieId) {
        movieDao.delete(movieId);
    }

    @Override
    public List<MovieVO> getAllMovies() {
        return movieDao.selectAll();
    }

    @Override
    public MovieVO getMovieById(int movieId) {
        return movieDao.selectById(movieId);
    }

    @Override
    public List<MovieVO> searchMoviesByTitle(String keyword) {
        return movieDao.searchByTitle(keyword);
    }

    @Override
    public List<MovieVO> filterMoviesByGenre(String genre) {
        return movieDao.serchByGenre(genre);
    }
}

