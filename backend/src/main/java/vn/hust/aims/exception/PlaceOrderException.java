package vn.hust.aims.exception;

public class PlaceOrderException extends RuntimeException {

	private static final long serialVersionUID = 1091337136123906298L;

	public PlaceOrderException() {

	}

	public PlaceOrderException(String message) {
		super(message);
	}

}
