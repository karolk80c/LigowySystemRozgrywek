package pl.karolkolarczyk.lgs.exception;

public class UnacceptableResultException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	int firstPlayerScore;
	int secondPlayerScore;

	public UnacceptableResultException(int firstPlayerScore, int secondPlayerScore) {
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
