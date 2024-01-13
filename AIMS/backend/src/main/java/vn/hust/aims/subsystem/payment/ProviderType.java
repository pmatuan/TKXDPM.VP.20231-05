package vn.hust.aims.subsystem.payment;

public enum ProviderType {
  UNKNOWN(0, "UNKNOWN"),
  VNPAY(1, "VNPAY"),
  PAYPAL(2, "PAYPAL"),
  HUSTPAY(2, "HUSTPAY");

  private final int intValue;
  private final String stringValue;

  ProviderType(int intValue, String stringValue) {
    this.intValue = intValue;
    this.stringValue = stringValue;
  }

  public static ProviderType from(int intValue) {
    for (ProviderType providerType : ProviderType.values()) {
      if (providerType.intValue == intValue) {
        return providerType;
      }
    }
    return UNKNOWN;
  }

  public static ProviderType from(String stringValue) {
    for (ProviderType providerType : ProviderType.values()) {
      if (providerType.stringValue.equals(stringValue)) {
        return providerType;
      }
    }
    return UNKNOWN;
  }
}
