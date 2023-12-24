package vn.hust.aims.subsystem.provider.paypalsubsystem;

import java.text.DecimalFormat;
import java.util.Base64;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class PaypalUtil {

  // PaypalUtil - PaypalConfig: Data coupling
  // Dữ liệu từ PaypalConfig được sử dụng để thực hiện tính toán và xây dựng dữ liệu trong PaypalUtil.

  // Mức độ cohesion: Functional Cohesion
  // Lớp này chứa các phương thức liên quan chặt chẽ đến các chức năng cụ thể của việc giao tiếp với Paypal.
  // Bao gồm tạo Authorization, tạo Access Token, tạo HttpHeaders, thực hiện HTTP request, và chuyển đổi tiền tệ.

  public String getAuth() {
    String auth = PaypalConfig.CLIENT_ID + ":" + PaypalConfig.APP_SECRET;
    return Base64.getEncoder().encodeToString(auth.getBytes());
  }

  public String generateAccessToken() {
    String auth = getAuth();
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.set("Authorization", "Basic " + auth);

    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    HttpEntity<?> request = new HttpEntity<>(requestBody, headers);
    requestBody.add("grant_type", "client_credentials");

    ResponseEntity<String> response = restTemplate.postForEntity(
        PaypalConfig.BASE_URL + "/v1/oauth2/token",
        request,
        String.class
    );

    if (response.getStatusCode() == HttpStatus.OK) {
      return new JSONObject(response.getBody()).getString("access_token");
    } else {
      return "Unavailable to get ACCESS TOKEN, STATUS CODE " + response.getStatusCode();
    }
  }

  public HttpHeaders createHttpHeaders() {
    String accessToken = generateAccessToken();
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + accessToken);
    headers.add("Content-Type", "application/json");
    headers.add("Accept", "application/json");
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }

  public HttpEntity<String> createHttpEntity(String requestJson) {
    HttpHeaders headers = createHttpHeaders();
    return new HttpEntity<>(requestJson, headers);
  }

  public ResponseEntity<Object> performHttpRequest(String url, HttpMethod method,
      HttpEntity<String> entity) {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.exchange(url, method, entity, Object.class);
  }

  public Double convertVNDToDollar(Double vnd) {

    Double amountDollar = vnd / PaypalConfig.usdToVndExchangeRate;

    DecimalFormat df = new DecimalFormat("#.##");
    amountDollar = Double.valueOf(df.format(amountDollar));

    return amountDollar;
  }
}
// Design principles - Kết quả kiểm tra các nguyên tắc liên quan:
// - SRP: không thoả mãn, vì lớp này có nhiều trách nhiệm như chuyển đổi VND sang $, các method phục vụ giao tiếp với Paypal.
// - OCP: thoả mãn, không cần sửa đổi mã nguồn hiện tại mà chỉ cần viết thêm method.