package vn.hust.aims.enumeration;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CoverType {
  @JsonEnumDefaultValue
  UNKNOWN(0, "UNKNOWN"),
  PAPERBACK(1, "PAPERBACK"),
  HARDCOVER(2, "HARDCOVER");

  private final int intValue;

  private final String stringValue;

  CoverType(int intValue, String stringValue) {
    this.intValue = intValue;
    this.stringValue = stringValue;
  }

  @Override
  public String toString() {
    return stringValue;
  }

  public static CoverType from(int intValue) {
    for (CoverType coverType : CoverType.values()) {
      if (coverType.intValue == intValue) {
        return coverType;
      }
    }
    return UNKNOWN;
  }

  public static CoverType from(String stringValue) {
    for (CoverType coverType : CoverType.values()) {
      if (coverType.stringValue.equals(stringValue)) {
        return coverType;
      }
    }
    return UNKNOWN;
  }
}
