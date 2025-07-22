package dao;

import java.util.List;
import vo.MovieVO;

public interface IMovieDAO {
    void insert(MovieVO vo);
    void delete(int movieId);
    List<MovieVO> selectAll();
    MovieVO selectById(int movieId);
    List<MovieVO> searchByTitle(String keyword);
    List<MovieVO> serchByGenre(String genre);
}
