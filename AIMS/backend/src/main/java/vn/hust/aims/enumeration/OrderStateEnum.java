package vn.hust.aims.enumeration;

public enum OrderStateEnum {
  UNKNOWN(0, "UNKNOWN"),
  ORDER_PLACING(1, "ORDER_PLACING"),
  PROCESSING(2, "PROCESSING"),
  CANCEL(3, "CANCEL"),
  REJECT(3, "REJECT"),
  PREPARING(3, "PREPARING"),
  SHIPPED(4, "SHIPPED"),
  DELIVERED(5, "DELIVERED");

  private final int intValue;

  private final String stringValue;

  OrderStateEnum(int intValue, String stringValue) {
    this.intValue = intValue;
    this.stringValue = stringValue;
  }

  public static OrderStateEnum from(int intValue) {
    for (OrderStateEnum orderStateEnum : OrderStateEnum.values()) {
      if (orderStateEnum.intValue == intValue) {
        return orderStateEnum;
      }
    }
    return UNKNOWN;
  }

  public static OrderStateEnum from(String stringValue) {
    for (OrderStateEnum orderStateEnum : OrderStateEnum.values()) {
      if (orderStateEnum.stringValue.equals(stringValue)) {
        return orderStateEnum;
      }
    }
    return UNKNOWN;
  }

  public int getIntValue() {
    return intValue;
  }

  public String getStringValue() {
    return stringValue;
  }
}
