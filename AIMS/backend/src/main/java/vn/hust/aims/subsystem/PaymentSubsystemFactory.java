package vn.hust.aims.subsystem;

import org.springframework.http.HttpStatus;
import vn.hust.aims.exception.AimsException;
import vn.hust.aims.exception.ErrorCodeList;
import vn.hust.aims.subsystem.provider.paypalsubsystem.PaypalSubsystem;
import vn.hust.aims.subsystem.provider.vnpaysubsystem.VNPaySubsystem;

public class PaymentSubsystemFactory {

  // PaymentSubsystemFactory - PaymentSubsystem: Data coupling
  // PaymentSubsystemFactory chỉ tương tác với PaymentSubsystem thông qua việc gọi phương thức get.
  // Nó sử dụng ProviderType để xác định loại dịch vụ thanh toán cần tạo ra.
  public static PaymentSubsystem get(ProviderType type) {
    switch (type) {
      // PaymentSubsystemFactory - ProviderType: Data coupling
      // PaymentSubsystemFactory sử dụng dữ liệu trong ProviderType để xác định loại dịch vụ
      // thanh toán cụ thể và quyết định tạo ra đối tượng nào tương ứng
      case VNPAY -> {
        return new VNPaySubsystem();
      }
      case PAYPAL -> {
        return new PaypalSubsystem();
      }
      // PaymentSubsystemFactory - AimsException: Data coupling
      // PaymentSubsystemFactory tương tác với AimsException thông qua truyền dữ liệu error code.
      default -> throw new AimsException(null, ErrorCodeList.PAYMENT_NOT_SUPPORTED,
          HttpStatus.BAD_REQUEST);
    }
  }
}
