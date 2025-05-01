package vo;

public class WishlistVO {
    private int wishlistId;
    private String memberId;
    private int movieId;
    private String createdAt;

    public WishlistVO() {}

    public WishlistVO(int wishlistId, String memberId, int movieId, String createdAt) {
        this.wishlistId = wishlistId;
        this.memberId = memberId;
        this.movieId = movieId;
        this.createdAt = createdAt;
    }

	public int getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(int wishlistId) {
		this.wishlistId = wishlistId;
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
