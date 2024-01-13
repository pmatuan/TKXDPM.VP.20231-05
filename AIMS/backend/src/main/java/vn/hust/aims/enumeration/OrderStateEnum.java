package vn.hust.aims.enumeration;

public enum OrderStateEnum {
  UNKNOWN(0, "UNKNOWN"),
  PROCESSING(1, "PROCESSING"),
  CANCEL(2, "CANCEL"),
  REJECT(2, "REJECT"),
  ACCEPT(2, "ACCEPT");

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
