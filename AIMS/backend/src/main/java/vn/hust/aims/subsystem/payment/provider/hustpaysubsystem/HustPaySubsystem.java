package vn.hust.aims.subsystem.payment.provider.hustpaysubsystem;

import vn.hust.aims.entity.order.Order;
import vn.hust.aims.subsystem.payment.PaymentSubsystem;
import vn.hust.aims.subsystem.payment.Provider;

@Provider("HUSTPAY")
public class HustPaySubsystem implements PaymentSubsystem {

  @Override
  public String payOrder(Double amount, String message) {
    return "http://localhost:8082/";
  }

  @Override
  public String refund(Order order) {
    return null;
  }
}
