package vn.hust.aims.subsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.http.HttpStatus;
import vn.hust.aims.exception.AimsException;
import vn.hust.aims.exception.ErrorCodeList;

public class PaymentSubsystemFactory {

  private static final Map<String, Class<? extends PaymentSubsystem>> paymentSubsystemMap = new HashMap<>();

  static {
    registerPaymentSubsystemClasses();
  }

  private static void registerPaymentSubsystemClasses() {
    Reflections reflections = new Reflections(new ConfigurationBuilder()
        .forPackages("vn.hust.aims.subsystem") // Package for paymentsubsystem
        .addScanners(new SubTypesScanner()));

    Set<Class<? extends PaymentSubsystem>> productClasses = reflections.getSubTypesOf(
        PaymentSubsystem.class);

    for (Class<? extends PaymentSubsystem> paymentSubsystemClass : productClasses) {
      Provider factoryAnnotation = paymentSubsystemClass.getAnnotation(Provider.class);
      if (factoryAnnotation != null) {
        String key = factoryAnnotation.value();
        paymentSubsystemMap.put(key, paymentSubsystemClass);
      }
    }
  }

  // PaymentSubsystemFactory - PaymentSubsystem: Data coupling
  // PaymentSubsystemFactory chỉ tương tác với PaymentSubsystem thông qua việc gọi phương thức get.
  // Nó sử dụng ProviderType để xác định loại dịch vụ thanh toán cần tạo ra.
  public static PaymentSubsystem get(ProviderType type) {
    try {
      Class<?> subsystemClass = paymentSubsystemMap.get(type.name());
      return (PaymentSubsystem) subsystemClass.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      // Handle exceptions appropriately
      throw new AimsException(null, ErrorCodeList.PAYMENT_NOT_SUPPORTED, HttpStatus.BAD_REQUEST);
    }
  }
}
