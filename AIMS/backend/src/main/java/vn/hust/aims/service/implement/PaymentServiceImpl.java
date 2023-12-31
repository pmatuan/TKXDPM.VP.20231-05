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
import vn.hust.aims.repository.order.OrderRepository;
import vn.hust.aims.repository.order.PaymentTransactionRepository;
import vn.hust.aims.service.MailService;
import vn.hust.aims.service.OrderService;
import vn.hust.aims.service.PaymentService;
import vn.hust.aims.service.dto.input.email.SendEmailInput;
import vn.hust.aims.service.dto.input.payment.RefundInput;
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
import vn.hust.aims.utils.TimeUtils;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final OrderRepository orderRepository;
  private PaymentSubsystem paymentSubsystem;

  public PayOrderOutput payOrder(PayOrderInput input) {

    paymentSubsystem = PaymentSubsystemFactory.get(ProviderType.from(input.getProvider()));
    Order order = orderRepository.findById(input.getOrderId()).get();

    Double amount = order.getTotal();
    String message = order.getId();

    String url = paymentSubsystem.payOrder(amount, message);

    return PayOrderOutput.from(url);
  }

  public void refund(RefundInput input) {
    paymentSubsystem = PaymentSubsystemFactory.get(
        ProviderType.from(input.getOrder().getPaymentTransaction().getPaymentMethod()));
    System.out.println(paymentSubsystem.refund(input.getOrder()));
  }

}
