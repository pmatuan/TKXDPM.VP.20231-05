package vn.hust.aims.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import vn.hust.aims.constant.Constant;

public class TimeUtils {
  public static Instant convertToInstant(String paymentTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    LocalDateTime localDateTime = LocalDateTime.parse(paymentTime, formatter);
    Instant instant = localDateTime.toInstant(Constant.VIETNAM_ZONE_OFFSET);
    return instant;
  }
  public static String formatInstant(Instant instant) {
    // Convert Instant to ZonedDateTime or OffsetDateTime
    ZonedDateTime zonedDateTime = instant.atZone(Constant.VIETNAM_ZONE_OFFSET);

    // Create a formatter for the desired format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    // Format the ZonedDateTime using the formatter
    return formatter.format(zonedDateTime);
  }
}
