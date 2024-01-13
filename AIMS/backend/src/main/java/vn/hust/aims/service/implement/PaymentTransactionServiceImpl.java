package vn.hust.aims.service.implement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.email.Param;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.entity.order.PaymentTransaction;
import vn.hust.aims.repository.order.PaymentTransactionRepository;
import vn.hust.aims.service.MailService;
import vn.hust.aims.service.OrderService;
import vn.hust.aims.service.PaymentTransactionService;
import vn.hust.aims.service.dto.input.email.SendEmailInput;
import vn.hust.aims.service.dto.input.payment.paypal.PaypalTransactionInput;
import vn.hust.aims.service.dto.input.payment.vnpay.VNPayTransactionInput;
import vn.hust.aims.service.dto.output.payment.paypal.PaypalTransactionOutput;
import vn.hust.aims.service.dto.output.payment.vnpay.VNPayTransactionOutput;
import vn.hust.aims.subsystem.payment.PaymentSubsystemFactory;
import vn.hust.aims.subsystem.payment.ProviderType;
import vn.hust.aims.subsystem.payment.provider.paypalsubsystem.PaypalSubsystem;
import vn.hust.aims.utils.TimeUtils;

@Service
@RequiredArgsConstructor
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

  private final OrderService orderService;
  private final PaymentTransactionRepository paymentTransactionRepository;
  private final MailService mailService;
  @Value(value = "${url.frontend}")
  private String frontendUrl;

  public VNPayTransactionOutput saveVNPayTransaction(VNPayTransactionInput input) {
    PaymentTransaction paymentTransaction = PaymentTransaction.builder()
        .id(input.getTransactionId())
        .paymentMethod("VNPAY")
        .status(input.getStatus())
        .amount(input.getAmount() / 100) // Do VNPAY lúc trả về * 100 số tiền thực
        .timestamp(input.getPaymentTime())
        .content(input.getOrderInfo())
        .build();

    String orderId = input.getOrderInfo();
    Order order = orderService.getOrderById(orderId);

    paymentTransactionRepository.save(paymentTransaction);
    order.setPaymentTransaction(paymentTransaction);
    orderService.saveOrder(order);
    // TODO: refactor
    String return_url = frontendUrl + "/order-detail";
    Integer amount = input.getAmount().intValue() / 100;

    List<Param> params = Arrays.asList(
        Param.builder().key("orderId").value(orderId).build(),
        Param.builder().key("trace_order_link")
            .value(frontendUrl + "/trace-order?orderId=" + orderId).build()
    );

    mailService.send(
        SendEmailInput.builder()
            .status(input.getStatus())
            .destination(orderService.getCustomerEmailFromOrder(input.getOrderInfo()))
            .templateName("Xác nhận đơn hàng")
            .params(params)
            .build()
    );

    return VNPayTransactionOutput.from(
        return_url + "?paymentStatus=" + input.getStatus() + "&orderId=" + input.getOrderInfo()
            + "&amount=" + amount + "&paymentTime=" + input.getPaymentTime()
            + "&transactionId=" + input.getTransactionId() + "&paymentMethod=" + "VNPay");
  }

  public PaypalTransactionOutput savePaypalTransaction(PaypalTransactionInput input) {
    PaypalSubsystem paypalSubsystem = (PaypalSubsystem) PaymentSubsystemFactory.get(
        ProviderType.from("PAYPAL"));
    Object response = paypalSubsystem.capturePayment(input.getId());

    System.out.println(response);

    String transactionId = "";
    Double amount = 0.0;
    String paymentTime = "";

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonResponse = objectMapper.writeValueAsString(response);
      JsonNode root = objectMapper.readTree(jsonResponse);

      transactionId = root.get("id").asText() + "-" + root
          .get("purchase_units")
          .get(0)
          .get("payments")
          .get("captures")
          .get(0)
          .get("id")
          .asText();

      amount = root
          .get("purchase_units")
          .get(0)
          .get("payments")
          .get("captures")
          .get(0)
          .get("amount")
          .get("value")
          .asDouble();

      paymentTime = root
          .get("purchase_units")
          .get(0)
          .get("payments")
          .get("captures")
          .get(0)
          .get("create_time")
          .asText();
    } catch (Exception e) {
      e.printStackTrace();
    }

    String return_url = frontendUrl + "/order-detail";

    PaymentTransaction paymentTransaction = PaymentTransaction.builder()
        .id(transactionId)
        .paymentMethod("PAYPAL")
        .status(true)
        .amount(amount) // Do VNPAY lúc trả về * 100 số tiền thực
        .timestamp(TimeUtils.convertToInstant(paymentTime))
        .build();

    Order order = orderService.getOrderById(input.getOrderId());

    paymentTransactionRepository.save(paymentTransaction);
    order.setPaymentTransaction(paymentTransaction);
    orderService.saveOrder(order);

    List<Param> params = Arrays.asList(
        Param.builder().key("orderId").value(input.getOrderId()).build(),
        Param.builder().key("trace_order_link")
            .value(frontendUrl + "/trace-order?orderId=" + input.getOrderId()).build()
    );

    mailService.send(
        SendEmailInput.builder()
            .status(true)
            .destination(orderService.getCustomerEmailFromOrder(input.getOrderId()))
            .templateName("Xác nhận đơn hàng")
            .params(params)
            .build()
    );

    return PaypalTransactionOutput.from(
        return_url + "?paymentStatus=true" + "&orderId=" + input.getOrderId()
            + "&amount=" + amount + "&paymentTime=" + paymentTime
            + "&transactionId=" + transactionId + "&paymentMethod=" + "Paypal");
  }
}
