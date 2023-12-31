package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.controller.dto.request.payment.PayOrderRequest;
import vn.hust.aims.controller.dto.request.payment.paypal.PaypalTransactionRequest;
import vn.hust.aims.controller.dto.request.payment.vnpay.VNPayTransactionRequest;
import vn.hust.aims.controller.dto.response.payment.PayOrderResponse;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.PaymentService;
import vn.hust.aims.service.PaymentTransactionService;
import vn.hust.aims.service.dto.output.payment.PayOrderOutput;
import vn.hust.aims.service.dto.output.payment.paypal.PaypalTransactionOutput;
import vn.hust.aims.service.dto.output.payment.vnpay.VNPayTransactionOutput;
import vn.hust.aims.utils.ResponseUtil;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {
  // Mức độ cohesion: Functional Cohesion
  // Lớp này thực hiện các hoạt động liên quan đến thanh toán.
  // Mọi phương thức và thuộc tính đều được thiết kế để hỗ trợ chức năng thanh toán.

  private final PaymentService paymentService;
  private final PaymentTransactionService paymentTransactionService;
  // PaymentController - PayOrderRequest: Data coupling
  // Lớp PaymentController nhận dữ liệu từ client đến hệ thống qua đối tượng PayOrderRequest .
  // Dữ liệu được truyền chỉ chứa những thông tin cần thiết.
  @PostMapping("/payorder")
  public ResponseEntity<AimsCommonResponse<Object>> payOrder(@RequestBody PayOrderRequest payOrderRequest) {

    // PaymentController - PaymentSubsystemFactory: Data coupling
    // PaymentController truyền ProviderType trong PayOrderRequest sang PaymentSubsystemFactory để xác định loại dịch vụ thanh toán cần thực hiện.


    // PaymentController - PaymentSubsystem: Data coupling
    // PaymentController truyền dữ liệu vừa đủ sang lớp PaymentSubsystem thông qua phương thức payOrder.
    PayOrderOutput output = paymentService.payOrder(payOrderRequest.toInput());

    // PaymentController - PayOrderResponse: Data coupling
    // PaymentController truyền dữ liệu vừa đủ cho lớp PayOrderResponse để tạo thành phản hồi cho client.
    return ResponseUtil.toSuccessCommonResponse(
        PayOrderResponse.from(output)
    );
  }

  @GetMapping("/vnpay-return")
  public String handleVNPayReturn(VNPayTransactionRequest request){
    VNPayTransactionOutput output = paymentTransactionService.saveVNPayTransaction(request.toInput());

    return "redirect:" + output.getUrl() ;

  }

  @PostMapping("/paypal-return")
  public String handlePayPalReturn(@RequestBody PaypalTransactionRequest request){
    PaypalTransactionOutput output =  paymentTransactionService.savePaypalTransaction(request.toInput());
    return output.getUrl();
  }
}
// Design principles - Kết quả kiểm tra các nguyên tắc liên quan:
// - SRP: thoả mãn, lớp PaymentController có trách nhiệm chính là xử lý các hoạt động liên quan đến thanh toán
// - OCP: thoả mãn, có thể mở rộng bằng cách thêm dịch vụ thanh toán mới mà không làm ảnh hưởng đến mã nguồn hiện tại do sử dụng PaymentSubsystemFactory để tạo đối tượng PaymentSubsystem
// - DIP: thoả mãn, do phụ thuộc vào interfacce PaymentSubsystem chứ không phải một triển khai cụ thể