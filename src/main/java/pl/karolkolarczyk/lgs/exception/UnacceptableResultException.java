package pl.karolkolarczyk.lgs.exception;

public class UnacceptableResultException extends RuntimeException {

	private static final long serialVersionUID = 14323424L;
	int firstPlayerScore;
	int secondPlayerScore;

	public UnacceptableResultException(int firstPlayerScore, int secondPlayerScore) {
		super();
		this.firstPlayerScore = firstPlayerScore;
		this.secondPlayerScore = secondPlayerScore;
	}

	public UnacceptableResultException(int firstPlayerScore, int secondPlayerScore, String message) {
		super(message);
		this.firstPlayerScore = firstPlayerScore;
		this.secondPlayerScore = secondPlayerScore;
	}

	public int getFirstPlayerScore() {
		return firstPlayerScore;
	}

	public int getSecondPlayerScore() {
		return secondPlayerScore;
	}

}
