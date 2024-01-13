package vn.hust.aims.subsystem.payment.provider.paypalsubsystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.subsystem.payment.PaymentSubsystem;
import vn.hust.aims.subsystem.payment.Provider;

@Provider("PAYPAL")
public class PaypalSubsystem implements PaymentSubsystem {
  // Mức độ cohesion: Functional Cohesion
  // Lớp này chứa các phương thức và thuộc tính liên quan đến chức năng thanh toán qua Paypal.

  private PaypalUtil paypalUtil;

  public PaypalSubsystem() {
    this.paypalUtil = new PaypalUtil();
  }

  // PaypalSubsystem - PaypalUtil: Data coupling
  // Phương thức payOrder của PaypalSubsystem được triển khai từ interface PaymentSubsystem,
  // trong đó có sử dụng PaypalUtil để xây dựng HttpEntity, thực hiện HTTP request và trả về PayOrderOutput.
  // PaypalSubsystem truyền vừa đủ dữ liệu sang PaypalUtil.
  @Override
  public String payOrder(Double amount, String message) {
    Double amountDollar = paypalUtil.convertVNDToDollar(amount);

    System.out.println("Dollar: " + amountDollar);

    String requestJson =
        "{\"intent\":\"CAPTURE\",\"purchase_units\":[{\"amount\":{\"currency_code\":\"USD\",\"value\":\""
            + amountDollar + "\"}}]}";
    HttpEntity<String> entity = paypalUtil.createHttpEntityForPay(requestJson);

    ResponseEntity<Object> response = paypalUtil.performHttpRequest(
        PaypalConfig.BASE_URL + "/v2/checkout/orders",
        HttpMethod.POST,
        entity
    );

    Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
    String id = (String) responseBody.get("id");
    System.out.println("ID paypal: " + id);

    // PaypalSubsystem - PayOrderOutput: Data coupling
    // PaypalSubsystem truyền dữ liệu vừa đủ cho PayOrderOutput để tạo thành url thanh toán trả về phía PaymentController.
    if (response.getStatusCode() == HttpStatus.CREATED) {
      return id;
    } else {
      return null;
    }

  }

  @Override
  public String refund(Order order) {
    Double amountDollar = paypalUtil.convertVNDToDollar(order.getTotal());

    String[] parts = order.getPaymentTransaction().getId().split("-");
    String transactionId = parts[0];
    String captureId = parts[1];

    System.out.println("Capture Id: " + captureId);

    String requestJson = "{"
        + "\"amount\": {\"value\": \"" + amountDollar + "\", \"currency_code\": \"USD\"}, "
        + "\"invoice_id\": \"" + transactionId + "\", "
        + "\"note_to_payer\": \"DefectiveProduct\""
        + "}";

    HttpEntity<String> entity = paypalUtil.createHttpEntityForRefund(requestJson);

    ResponseEntity<Object> response = paypalUtil.performHttpRequest(
        PaypalConfig.BASE_URL + "/v2/payments/captures/" + captureId + "/refund",
        HttpMethod.POST,
        entity
    );

    System.out.println(response.getBody());

    return null;

  }

  public Object capturePayment(String orderId) {
    HttpEntity<String> entity = paypalUtil.createHttpEntityForPay(null);

    ResponseEntity<Object> response = paypalUtil.performHttpRequest(
        PaypalConfig.BASE_URL + "/v2/checkout/orders/" + orderId + "/capture",
        HttpMethod.POST,
        entity
    );

    return response.getBody();
  }

}
// Design principles - Kết quả kiểm tra các nguyên tắc liên quan:
// - SRP: thoả mãn, vì lớp này chỉ chịu trách nhiệm thực hiện thanh toán qua Paypal.
// - OCP: thoả mãn, vì để thêm một loại thanh toán mới (ví dụ: refund), không cần sửa đổi mã nguồn hiện tại mà chỉ cần viết thêm method.
// - DIP: không thoả mãn, vì phụ thuộc vào một lớp cụ thể là PaypalUtil