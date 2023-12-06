package vn.hust.aims.subsystem;

import org.springframework.http.HttpStatus;
import vn.hust.aims.exception.AimsException;
import vn.hust.aims.exception.ErrorCodeList;

public class PaymentSubsystemFactory {

  // PaymentSubsystemFactory - PaymentSubsystem: Data coupling
  // PaymentSubsystemFactory chỉ tương tác với PaymentSubsystem thông qua việc gọi phương thức get.
  // Nó sử dụng ProviderType để xác định loại dịch vụ thanh toán cần tạo ra.
  public static PaymentSubsystem get(ProviderType type) {
    try {
      // Using reflection to create an instance based on the provider type
      String className =
          "vn.hust.aims.subsystem.provider." + type.name().toLowerCase() + "subsystem."
              + type.name() + "Subsystem";
      Class<?> subsystemClass = Class.forName(className);
      return (PaymentSubsystem) subsystemClass.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      // Handle exceptions appropriately
      throw new AimsException(null, ErrorCodeList.PAYMENT_NOT_SUPPORTED, HttpStatus.BAD_REQUEST);
    }
  }
}
