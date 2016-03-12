package pl.karolkolarczyk.lgs.exception;

public class notExistingPlaceException extends Exception {

	private static final long serialVersionUID = 23123131L;

	public notExistingPlaceException() {
		super();
	}

	public notExistingPlaceException(String message) {
		super(message);
	}

}
