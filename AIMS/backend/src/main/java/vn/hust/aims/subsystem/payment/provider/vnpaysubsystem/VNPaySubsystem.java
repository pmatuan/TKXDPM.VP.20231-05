package vn.hust.aims.subsystem.payment.provider.vnpaysubsystem;

import java.util.Map;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.subsystem.payment.PaymentSubsystem;
import vn.hust.aims.subsystem.payment.Provider;
import vn.hust.aims.utils.TimeUtils;

@Provider("VNPAY")
public class VNPaySubsystem implements PaymentSubsystem {

  // Mức độ cohesion: Functional Cohesion
  // Lớp này chứa một phương thức duy nhất và tất cả các thành phần đều liên quan chặt chẽ đến
  // chức năng thanh toán qua VNPay.
  private VNPayUtil vnPayUtil;

  public VNPaySubsystem() {
    this.vnPayUtil = new VNPayUtil();
  }

  @Override
  public String payOrder(Double amount, String message) {

    String returnUrl = "http://localhost:8080/api/v1/payment/vnpay-return";

    // VNPaySubsystem - VNPayUtil: Data coupling
    // Phương thức payOrder của VNPaySubsystem được triển khai từ interface PaymentSubsystem,
    // trong đó có sử dụng VNPayUtil để xây dựng URL và trả về PayOrderOutput.
    // VNPaySubsystem truyền vừa đủ dữ liệu PayOrderInput và HttpServletRequest sang VNPayUtil.
    String vnpayUrl = vnPayUtil.buildQueryUrl(amount, message, returnUrl);

    // VNPaySubsystem - PayOrderOutput: Data coupling
    // VNPaySubsystem truyền dữ liệu vừa đủ cho PayOrderOutput để tạo thành url thanh toán trả về phía PaymentController.
    return vnpayUrl;
  }

  @Override
  public String refund(Order order) {

    String response = vnPayUtil.buildRefundQueryUrl(order.getTotal(), order.getId(),
        order.getPaymentTransaction().getId(),
        TimeUtils.formatInstant(order.getCreatedAt()));

    return response;
  }

}
// Design principles - Kết quả kiểm tra các nguyên tắc liên quan:
// - SRP: thoả mãn, vì lớp này chỉ chịu trách nhiệm thực hiện thanh toán qua VNPay.
// - OCP: thoả mãn, vì để thêm một loại thanh toán mới (ví dụ: refund), không cần sửa đổi mã nguồn hiện tại mà chỉ cần viết thêm method.
// - DIP: không thoả mãn, vì phụ thuộc vào một lớp cụ thể là VNPayUtil