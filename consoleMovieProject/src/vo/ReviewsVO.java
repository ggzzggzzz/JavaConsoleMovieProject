package vo;

public class ReviewsVO {
    private int reviewId;
    private String memberId;
    private int movieId;
    private double rating;
    private String content;
    private int likeCount;
    private String createdAt;

    public ReviewsVO() {}

    public ReviewsVO(int reviewId, String memberId, int movieId, double rating,
                    String content, int likeCount, String createdAt) {
        this.reviewId = reviewId;
        this.memberId = memberId;
        this.movieId = movieId;
        this.rating = rating;
        this.content = content;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
    }

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
