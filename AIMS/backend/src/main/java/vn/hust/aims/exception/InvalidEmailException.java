package vn.hust.aims.exception;

public class InvalidEmailException extends InvalidDeliveryInfoException {

	private static final long serialVersionUID = 1091337136123906298L;

	public InvalidEmailException() {

	}

	public InvalidEmailException(String message) {
		super(message);
	}

}