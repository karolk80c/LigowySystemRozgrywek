package pl.karolkolarczyk.lgs.exception;

public class NotExistingPlaceException extends RuntimeException {

	public NotExistingPlaceException() {
		super();
	}

	public NotExistingPlaceException(String message) {
		super(message);
	}

}
