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

    String vnpayUrl = vnPayUtil.buildQueryUrl(input.getAmount(), input.getMessage(), returnUrl);

    return PayOrderOutput.fromUrl(vnpayUrl);
  }

}
