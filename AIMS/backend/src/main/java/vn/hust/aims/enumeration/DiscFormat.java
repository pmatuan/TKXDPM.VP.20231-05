package vn.hust.aims.enumeration;

public enum DiscFormat {
  UNKNOWN(0, "UNKNOWN"),
  BLURAY(1, "BLURAY"),
  HDDVD(2, "HDDVD");

  private final int intValue;

  private final String stringValue;

  DiscFormat(int intValue, String stringValue) {
    this.intValue = intValue;
    this.stringValue = stringValue;
  }

  public static DiscFormat from(int intValue) {
    for (DiscFormat discFormat : DiscFormat.values()) {
      if (discFormat.intValue == intValue) {
        return discFormat;
      }
    }
    return UNKNOWN;
  }

  public static DiscFormat from(String stringValue) {
    for (DiscFormat discFormat : DiscFormat.values()) {
      if (discFormat.stringValue.equals(stringValue)) {
        return discFormat;
      }
    }
    return UNKNOWN;
  }
}
