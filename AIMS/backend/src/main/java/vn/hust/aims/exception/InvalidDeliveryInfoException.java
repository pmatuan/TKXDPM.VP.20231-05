package vn.hust.aims.exception;

public class InvalidDeliveryInfoException extends RuntimeException {

	private static final long serialVersionUID = 1091337136123906298L;

	public InvalidDeliveryInfoException() {

	}

	public InvalidDeliveryInfoException(String message) {
		super(message);
	}

}