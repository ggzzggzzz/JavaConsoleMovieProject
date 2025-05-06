package ui;

import java.util.List;
import java.util.Scanner;

import service.MovieService;
import vo.MovieVO;

public class MovieListUI {

    private MovieService movieService = MovieService.getInstance();
    private Scanner sc = new Scanner(System.in);

    public void start() {
    	
        List<MovieVO> list = movieService.getAllMovies();
        System.out.println("영화 개수: " + list.size());

        
        try {
        	
        
        System.out.println("\n[영화 목록]");
        if (list == null || list.isEmpty()) {
            System.out.println("영화 데이터가 없습니다.");
            return;
        }
        for (MovieVO movie : list) {
            System.out.printf("ID: %d | 제목: %s | 장르: %s\n", movie.getMovieId(), movie.getTitle(), movie.getGenre());
        }

        System.out.print("상세 정보를 볼 영화 ID 입력 (0: 취소): ");
        int id = Integer.parseInt(sc.nextLine());

        if (id != 0) {
            MovieVO movie = movieService.getMovieById(id);
            if (movie == null) {
                System.out.println("영화를 찾을 수 없습니다.");
                return;
            }
            System.out.println("== 상세 정보 ==");
            System.out.println("제목: " + movie.getTitle());
            System.out.println("감독: " + movie.getDirector());
            System.out.println("장르: " + movie.getGenre());
            System.out.println("줄거리: " + movie.getSynopsis());
            System.out.println("상영 시간: " + movie.getRunningTime() + "분");
        }
     
        } catch (Exception e) {
        e.printStackTrace();  
    }
}
}
