package vn.hust.aims.service.dto.input.placeorder;

import java.time.Instant;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.enumeration.ProvinceEnum;

@Data
@SuperBuilder
public class UpdateDeliveryInfoInput {

  private String orderId;
  private String customerName;
  private String email;
  private String phoneNumber;
  private ProvinceEnum province;
  private String address;
  private Boolean isOrderForRushDelivery;
  private Instant deliveryTime;
  private String deliveryInstruction;
}
