package vn.hust.aims.subsystem.payment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import vn.hust.aims.exception.PaymentNotSupportedException;

public class PaymentSubsystemFactory {
  // Mức độ cohesion: Functional Cohesion
  // Lớp này có các phương thức liên quan đến việc đăng ký và truy xuất các loại PaymentSubsystem.

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
      throw new PaymentNotSupportedException();
    }
  }
}
// Design principles - Kết quả kiểm tra các nguyên tắc liên quan:
// - SRP: thoả mãn, vì lớp này chỉ chịu trách nhiệm việc đăng ký và truy xuất các loại PaymentSubsystem.
// - OCP: thoả mãn, vì không cần sửa mã nguồn để thêm một loại PaymentSubsystem mới.
// - DIP: thoả mãn, do phụ thuộc vào interfacce PaymentSubsystem chứ không phải một triển khai cụ thể