package vo;

public class MoviesVO {
    private int movieId;
    private String title;
    private String genre;
    private String director;
    private int runningTime;
    private String releaseDate;
    private String synopsis;
    private String createdAt;

    public MoviesVO() {}

    public MoviesVO(int movieId, String title, String genre, String director,
                   int runningTime, String releaseDate, String synopsis, String createdAt) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.createdAt = createdAt;
    }

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
