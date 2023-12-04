package vn.hust.aims.exception;

public enum ErrorCodeList {
  InternalServerError("500", "Internal Server Error", true),
  InvalidParameter("PARAM_4001", "Invalid Parameter"),
  CART_NOT_FOUND("CART_001", "Cart not found"),
  MEDIA_NOT_FOUND("MEDIA_001", "Media not found"),
  ORDER_NOT_FOUND("ORDER_001", "Order not found"),
  RUSH_ORDER_NOT_FOUND("RUSH_ORDER_001", "Rush order not found"),
  CART_MEDIA_NOT_FOUND("CART_MEDIA_001", "Cart media not found"),
  ORDER_MEDIA_NOT_FOUND("ORDER_MEDIA_001", "Order media not found"),
  QUANTITY_NOT_ENOUGH("QUANTITY_001", "Quantity of media not enough"),
  CITY_NOT_SUPPORT_RUSH_DELIVERY("CITY_NOT_SUPPORT_RUSH_DELIVERY_001", "City not support rush delivery"),
  INVALID_TIME("INVALID_TIME_001", "Invalid time"),
  INVALID_EMAIL("INVALID_EMAIL_001", "Invalid email"),
  INVALID_PHONE_NUMBER("INVALID_PHONE_NUMBER_001", "Invalid phone number"),
  INVALID_PROVINCE("INVALID_PROVINCE_001", "Invalid province"),
  PAYMENT_NOT_SUPPORTED("PAYMENT_001", "Payment type not supported"),;



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
