package vn.hust.aims.subsystem.provider.vnpaysubsystem;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class VNPayUtil {

  private VNPayConfig vnPayConfig;

  public VNPayUtil() {
    vnPayConfig = new VNPayConfig("ORRJM9BG",
        "IXGFEGJRHNESWTQMSJOUEMDCUTJNOVME"); // TODO: Load from application.properties
  }

  public static String hashAllFields(Map<String, String> fields, String vnp_HashSecret) {
    List<String> fieldNames = new ArrayList<>(fields.keySet());
    Collections.sort(fieldNames);
    StringBuilder sb = new StringBuilder();
    Iterator<String> itr = fieldNames.iterator();

    while (itr.hasNext()) {
      String fieldName = itr.next();
      String fieldValue = fields.get(fieldName);

      if ((fieldValue != null) && (fieldValue.length() > 0)) {
        sb.append(fieldName).append("=").append(fieldValue);
      }
      if (itr.hasNext()) {
        sb.append("&");
      }
    }
    return hmacSHA512(vnp_HashSecret, sb.toString());
  }

  public static String hmacSHA512(final String key, final String data) {
    try {
      if (key == null || data == null) {
        throw new NullPointerException();
      }
      final Mac hmac512 = Mac.getInstance("HmacSHA512");
      byte[] hmacKeyBytes = key.getBytes();
      final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
      hmac512.init(secretKey);
      byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
      byte[] result = hmac512.doFinal(dataBytes);
      StringBuilder sb = new StringBuilder(2 * result.length);
      for (byte b : result) {
        sb.append(String.format("%02x", b & 0xff));
      }
      return sb.toString();
    } catch (Exception ex) {
      return "";
    }
  }

  public String buildQueryUrl(Double amount, String vnp_OrderInfo, String vnp_Returnurl) {
    Map<String, String> vnp_Params = new HashMap<>();

    vnp_Params.put("vnp_Version", vnPayConfig.getVnp_Version());
    vnp_Params.put("vnp_Command", vnPayConfig.getVnp_Command());
    vnp_Params.put("vnp_TmnCode", vnPayConfig.getVnp_TmnCode());
    vnp_Params.put("vnp_Amount", String.valueOf((int) (amount * 100))); // VNPay cần int chỗ này
    vnp_Params.put("vnp_CurrCode", vnPayConfig.getVnp_CurrCode());
    vnp_Params.put("vnp_TxnRef", vnPayConfig.getVnp_TxnRef());
    vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
    vnp_Params.put("vnp_OrderType", "order-type"); // TODO: Change
    vnp_Params.put("vnp_Locale", vnPayConfig.getVnp_Locale());
    vnp_Params.put("vnp_ReturnUrl", vnp_Returnurl + "/vnpay-return"); // TODO: Change
    vnp_Params.put("vnp_IpAddr", vnPayConfig.getVnp_IpAddr());

    Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    String vnp_CreateDate = formatter.format(cld.getTime());
    vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

    cld.add(Calendar.MINUTE, 15);
    String vnp_ExpireDate = formatter.format(cld.getTime());
    vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

    List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
    Collections.sort(fieldNames);
    StringBuilder hashData = new StringBuilder();
    StringBuilder query = new StringBuilder();
    Iterator<String> itr = fieldNames.iterator();

    while (itr.hasNext()) {
      String fieldName = itr.next();
      String fieldValue = vnp_Params.get(fieldName);

      if ((fieldValue != null) && (fieldValue.length() > 0)) {
        buildHashAndQuery(hashData, query, fieldName, fieldValue);
        if (itr.hasNext()) {
          query.append('&');
          hashData.append('&');
        }
      }
    }

    String queryUrl = query.toString();
    String vnp_SecureHash = hmacSHA512(vnPayConfig.getVnp_HashSecret(), hashData.toString());
    queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
    return vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
  }

  private void buildHashAndQuery(StringBuilder hashData, StringBuilder query, String fieldName,
      String fieldValue) {
    hashData.append(fieldName).append('=');
    try {
      hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
      query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
      query.append('=');
      query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public Integer orderReturn(HttpServletRequest request) {
    Map fields = new HashMap();
    for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
      String fieldName = null;
      String fieldValue = null;
      try {
        fieldName = URLEncoder.encode((String) params.nextElement(),
            StandardCharsets.US_ASCII.toString());
        fieldValue = URLEncoder.encode(request.getParameter(fieldName),
            StandardCharsets.US_ASCII.toString());
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      if ((fieldValue != null) && (fieldValue.length() > 0)) {
        fields.put(fieldName, fieldValue);
      }
    }

    String vnp_SecureHash = request.getParameter("vnp_SecureHash");
    if (fields.containsKey("vnp_SecureHashType")) {
      fields.remove("vnp_SecureHashType");
    }
    if (fields.containsKey("vnp_SecureHash")) {
      fields.remove("vnp_SecureHash");
    }
    String signValue = hashAllFields(fields, vnPayConfig.getVnp_HashSecret());
    if (signValue.equals(vnp_SecureHash)) {
      if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
        return 1;
      } else {
        return 0;
      }
    } else {
      return -1;
    }
  }
}
