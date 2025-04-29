package vo;

public class ReservationsVO {
    private int reservationId;
    private String memberId;
    private int movieId;
    private String showTime;
    private String seatNumber;
    private String paymentMethod;
    private int paymentAmount;
    private String reservationStatus;
    private String reservedAt;

    public ReservationsVO() {}

    public ReservationsVO(int reservationId, String memberId, int movieId, String showTime,
                         String seatNumber, String paymentMethod, int paymentAmount,
                         String reservationStatus, String reservedAt) {
        this.reservationId = reservationId;
        this.memberId = memberId;
        this.movieId = movieId;
        this.showTime = showTime;
        this.seatNumber = seatNumber;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
        this.reservationStatus = reservationStatus;
        this.reservedAt = reservedAt;
    }

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
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

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public String getReservedAt() {
		return reservedAt;
	}

	public void setReservedAt(String reservedAt) {
		this.reservedAt = reservedAt;
	}

}
