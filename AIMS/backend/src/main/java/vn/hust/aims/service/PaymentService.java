package vn.hust.aims.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.hust.aims.constant.Constant;
import vn.hust.aims.entity.email.Param;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.entity.order.PaymentTransaction;
import vn.hust.aims.repository.order.PaymentTransactionRepository;
import vn.hust.aims.service.dto.input.email.SendEmailInput;
import vn.hust.aims.service.dto.input.payment.paypal.PaypalTransactionInput;
import vn.hust.aims.service.dto.input.payment.vnpay.VNPayTransactionInput;
import vn.hust.aims.service.dto.output.payment.paypal.PaypalTransactionOutput;
import vn.hust.aims.service.dto.output.payment.vnpay.VNPayTransactionOutput;
import vn.hust.aims.subsystem.payment.PaymentSubsystem;
import vn.hust.aims.subsystem.payment.PaymentSubsystemFactory;
import vn.hust.aims.subsystem.payment.ProviderType;
import vn.hust.aims.service.dto.input.payment.PayOrderInput;
import vn.hust.aims.service.dto.output.payment.PayOrderOutput;
import vn.hust.aims.subsystem.payment.provider.paypalsubsystem.PaypalSubsystem;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final OrderService orderService;
  private final PaymentTransactionRepository paymentTransactionRepository;
  private final MailService mailService;
  @Value(value = "${url.frontend}")
  private String frontendUrl;
  private PaymentSubsystem paymentSubsystem;

  public PayOrderOutput payOrder(PayOrderInput input) {

    paymentSubsystem = PaymentSubsystemFactory.get(ProviderType.from(input.getProvider()));
    Order order = orderService.getOrderById(input.getOrderId());

    Double amount = order.getTotal();
    String message = order.getId();

    String url = paymentSubsystem.payOrder(amount, message);

    return PayOrderOutput.from(url);
  }

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
            .orderId(input.getOrderInfo())
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

    String transactionId = "";
    Double amount = 0.0;
    String paymentTime = "";

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonResponse = objectMapper.writeValueAsString(response);
      JsonNode root = objectMapper.readTree(jsonResponse);

      transactionId = root.get("id").asText();

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
        .timestamp(convertToInstant(paymentTime))
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
            .orderId(input.getOrderId())
            .templateName("Xác nhận đơn hàng")
            .params(params)
            .build()
    );

    return PaypalTransactionOutput.from(
        return_url + "?paymentStatus=true" + "&orderId=" + input.getOrderId()
            + "&amount=" + amount + "&paymentTime=" + paymentTime
            + "&transactionId=" + transactionId + "&paymentMethod=" + "Paypal");
  }

  private Instant convertToInstant(String paymentTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    LocalDateTime localDateTime = LocalDateTime.parse(paymentTime, formatter);
    Instant instant = localDateTime.toInstant(Constant.VIETNAM_ZONE_OFFSET);
    return instant;
  }

}