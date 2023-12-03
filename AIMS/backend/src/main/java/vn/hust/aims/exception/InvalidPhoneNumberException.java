package vn.hust.aims.exception;

public class InvalidPhoneNumberException extends InvalidDeliveryInfoException {

	private static final long serialVersionUID = 1091337136123906298L;

	public InvalidPhoneNumberException() {

	}

	public InvalidPhoneNumberException(String message) {
		super(message);
	}

}