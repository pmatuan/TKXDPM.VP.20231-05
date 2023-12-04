package vn.hust.aims.subsystem;

import org.springframework.http.HttpStatus;
import vn.hust.aims.exception.AimsException;
import vn.hust.aims.exception.ErrorCodeList;
import vn.hust.aims.subsystem.provider.paypalsubsystem.PaypalSubsystem;
import vn.hust.aims.subsystem.provider.vnpaysubsystem.VNPaySubsystem;

public class PaymentSubsystemFactory {

  public static PaymentSubsystem get(ProviderType type) {
    switch (type) {
      case VNPAY -> {
        return new VNPaySubsystem();
      }
      case PAYPAL -> {
        return new PaypalSubsystem();
      }
      default -> throw new AimsException(null, ErrorCodeList.PAYMENT_NOT_SUPPORTED,
          HttpStatus.BAD_REQUEST);
    }
  }
}
