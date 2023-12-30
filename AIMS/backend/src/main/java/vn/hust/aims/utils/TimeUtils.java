package vn.hust.aims.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import vn.hust.aims.constant.Constant;

public class TimeUtils {
  public static Instant convertToInstant(String paymentTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    LocalDateTime localDateTime = LocalDateTime.parse(paymentTime, formatter);
    Instant instant = localDateTime.toInstant(Constant.VIETNAM_ZONE_OFFSET);
    return instant;
  }
}
