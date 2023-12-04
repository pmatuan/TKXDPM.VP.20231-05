package vn.hust.aims.subsystem.provider.vnpaysubsystem;

import javax.servlet.http.HttpServletRequest;
import vn.hust.aims.subsystem.PaymentSubsystem;
import vn.hust.aims.subsystem.dto.input.PayOrderInput;
import vn.hust.aims.subsystem.dto.output.PayOrderOutput;

public class VNPaySubsystem implements PaymentSubsystem {

  private VNPayUtil vnPayUtil;

  public VNPaySubsystem(){
    this.vnPayUtil = new VNPayUtil();
  }

  @Override
  public PayOrderOutput payOrder(HttpServletRequest request, PayOrderInput input) {

    String returnUrl =
        request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

    // VNPaySubsystem - VNPayUtil: Data coupling
    // Phương thức payOrder của VNPaySubsystem được triển khai từ interface PaymentSubsystem,
    // trong đó có sử dụng VNPayUtil để xây dựng URL và trả về PayOrderOutput.
    // VNPaySubsystem truyền vừa đủ dữ liệu PayOrderInput và HttpServletRequest sang VNPayUtil.
    String vnpayUrl = vnPayUtil.buildQueryUrl(input.getAmount(), input.getMessage(), returnUrl);

    // VNPaySubsystem - PayOrderOutput: Data coupling
    // VNPaySubsystem truyền dữ liệu vừa đủ cho PayOrderOutput để tạo thành url thanh toán trả về phía PaymentController.
    return PayOrderOutput.fromUrl(vnpayUrl);
  }

}
