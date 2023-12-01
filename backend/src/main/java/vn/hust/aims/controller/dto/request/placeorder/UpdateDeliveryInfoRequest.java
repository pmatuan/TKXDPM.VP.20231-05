package vn.hust.aims.controller.dto.request.placeorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import vn.hust.aims.enumeration.ProvinceEnum;
import vn.hust.aims.exception.AimsException;
import vn.hust.aims.exception.ErrorCodeList;
import vn.hust.aims.service.dto.input.placeorder.UpdateDeliveryInfoInput;

import java.time.Instant;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDeliveryInfoRequest {

  private String customerName;
  private String email;
  private String phoneNumber;
  private String province;
  private String address;
  private Boolean isOrderForRushDelivery;
  private String deliveryTime;
  private String deliveryInstruction;

  public UpdateDeliveryInfoInput toInput(String orderId) {

    Instant parsedDeliveryTime = parseDeliveryTime();
    return UpdateDeliveryInfoInput.builder()
        .orderId(orderId)
        .customerName(customerName)
        .email(email)
        .phoneNumber(phoneNumber)
        .province(ProvinceEnum.fromString(province))
        .address(address)
        .isOrderForRushDelivery(isOrderForRushDelivery)
        .deliveryTime(parsedDeliveryTime)
        .deliveryInstruction(deliveryInstruction)
        .build();
  }

  private Instant parseDeliveryTime() {

    return Optional.ofNullable(deliveryTime)
        .map(time -> {
          try {
            return Instant.parse(time);
          } catch (Exception e) {
            throw new AimsException(null, ErrorCodeList.INVALID_TIME, HttpStatus.BAD_REQUEST);
          }
        })
        .orElse(null);
  }
}
