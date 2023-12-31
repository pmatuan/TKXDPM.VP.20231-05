package vn.hust.aims.exception;

public enum ErrorCodeList {
  InternalServerError("500", "Internal Server Error", true),
  InvalidParameter("PARAM_4001", "Invalid Parameter"),
  CART_NOT_FOUND("CART_001", "Cart not found"),
  MEDIA_NOT_FOUND("MEDIA_001", "Media not found"),
  MEDIA_IMAGE_NOT_FOUND("MEDIA_IMAGE_001", "Media image not found"),
  ORDER_NOT_FOUND("ORDER_001", "Order not found"),
  RUSH_ORDER_NOT_FOUND("RUSH_ORDER_001", "Rush order not found"),
  CART_MEDIA_NOT_FOUND("CART_MEDIA_001", "Cart media not found"),
  ORDER_MEDIA_NOT_FOUND("ORDER_MEDIA_001", "Order media not found"),
  QUANTITY_NOT_ENOUGH("QUANTITY_001", "Quantity of media not enough"),
  PROVINCE_NOT_SUPPORT_RUSH_DELIVERY("PROVINCE_NOT_SUPPORT_RUSH_DELIVERY_001",
      "Province not support rush delivery"),
  INVALID_TIME("INVALID_TIME_001", "Invalid time"),
  INVALID_EMAIL("INVALID_EMAIL_001", "Invalid email"),
  INVALID_PHONE_NUMBER("INVALID_PHONE_NUMBER_001", "Invalid phone number"),
  INVALID_PROVINCE("INVALID_PROVINCE_001", "Invalid province"),
  INVALID_ORDER_STATE("INVALID_ORDER_STATE_001", "Invalid order state"),
  CANNOT_CHANGE_ORDER_STATE("CANNOT_CHANGE_ORDER_STATE_001", "Cannot change order state"),
  CANNOT_CANCEL_ORDER("CANNOT_CANCEL_ORDER_001", "Cannot cancel order"),
  PAYMENT_NOT_SUPPORTED("PAYMENT_001", "Payment type not supported"),
  USER_NOT_FOUND("USER_001", "User not found"),
  NULL_EMAIL("EMAIL_001", "Email must not be null"),
  NULL_PASSWORD("PASSWORD_001", "Password must not be null"),
  INVALID_ROLE("ROLE_001", "User role has not been set or is invalid"),
  INVALID_BLOCKED_STATE("BLOCKED_STATE_001", "setIsBlocked value must be 0 or 1"),
  PRICE_CHANGE_LIMIT_EXCEEDED("CHANGE_001", "Exceeded daily price change limit"),
  UPDATE_DELETE_LIMIT_EXCEEDED("CHANGE_002", "Exceeded daily update or delete limit"),
  EMAIL_NOT_FOUND("LOGIN_001", "email not found"),
  WRONG_PASSWORD("LOGIN_002", "wrong password"),
  EMAIL_EXISTS("EMAIL_002", "Cannot create user: email already exists"),
  EMPTY_NAME("NAME_001", "Name cannot be empty")
  ;


  private final String code;
  private final String message;
  private Boolean shouldAlert = false;


  ErrorCodeList(String code, String message) {
    this.code = code;
    this.message = message;
  }

  ErrorCodeList(String code, String message, Boolean shouldAlert) {
    this.code = code;
    this.message = message;
    this.shouldAlert = shouldAlert;
  }

  public String toCode() {
    return this.code;
  }

  @Override
  public String toString() {
    return this.message;
  }

  public Boolean shouldAlert() {
    return shouldAlert;
  }
}
