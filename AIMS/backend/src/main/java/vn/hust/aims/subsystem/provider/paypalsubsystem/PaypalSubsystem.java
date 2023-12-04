package vn.hust.aims.subsystem.provider.paypalsubsystem;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.hust.aims.subsystem.PaymentSubsystem;
import vn.hust.aims.subsystem.dto.input.PayOrderInput;
import vn.hust.aims.subsystem.dto.output.PayOrderOutput;

public class PaypalSubsystem implements PaymentSubsystem {

  private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private PaypalUtil paypalUtil;

  public PaypalSubsystem() {
    this.paypalUtil = new PaypalUtil();
  }

  // PaypalSubsystem - PaypalUtil: Data coupling
  // Phương thức payOrder của PaypalSubsystem được triển khai từ interface PaymentSubsystem,
  // trong đó có sử dụng PaypalUtil để xây dựng HttpEntity, thực hiện HTTP request và trả về PayOrderOutput.
  // PaypalSubsystem truyền vừa đủ dữ liệu sang PaypalUtil.
  @Override
  public PayOrderOutput payOrder(HttpServletRequest request, PayOrderInput input) {
    Double amountDollar = paypalUtil.convertVNDToDollar(input.getAmount());

    String requestJson =
        "{\"intent\":\"CAPTURE\",\"purchase_units\":[{\"amount\":{\"currency_code\":\"USD\",\"value\":\""
            + amountDollar + "\"}}]}";
    HttpEntity<String> entity = paypalUtil.createHttpEntity(requestJson);

    ResponseEntity<Object> response = paypalUtil.performHttpRequest(
        PaypalConfig.BASE_URL + "/v2/checkout/orders",
        HttpMethod.POST,
        entity
    );

    // PaypalSubsystem - PayOrderOutput: Data coupling
    // PaypalSubsystem truyền dữ liệu vừa đủ cho PayOrderOutput để tạo thành url thanh toán trả về phía PaymentController.
    if (response.getStatusCode() == HttpStatus.CREATED) {
      LOGGER.log(Level.INFO, "ORDER CAPTURE");
      return PayOrderOutput.fromResponse(response.getBody());
    } else {
      LOGGER.log(Level.INFO, "FAILED CAPTURING ORDER");
      return null;
    }

  }
}
