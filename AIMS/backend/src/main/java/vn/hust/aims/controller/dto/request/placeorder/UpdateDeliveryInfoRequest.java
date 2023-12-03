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
import java.util.regex.Pattern;

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
    validateRequest();
    return UpdateDeliveryInfoInput.builder()
        .orderId(orderId)
        .customerName(customerName)
        .email(email)
        .phoneNumber(phoneNumber)
        .province(ProvinceEnum.fromString(province))
        .address(address)
        .isOrderForRushDelivery(isOrderForRushDelivery)
        .deliveryTime(parseDeliveryTime())
        .deliveryInstruction(deliveryInstruction)
        .build();
  }

  private void validateRequest() {
    validateEmail();
    validatePhoneNumber();
    validateProvince();
    if (isOrderForRushDelivery) {
      validateRushDelivery();
    }
  }

  private void validateEmail() {
    if (!isValidEmail(email)) {
      throw new AimsException(null, ErrorCodeList.INVALID_EMAIL, HttpStatus.BAD_REQUEST);
    }
  }

  private boolean isValidEmail(String email) {
    return Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email);
  }

  private void validatePhoneNumber() {
    if (phoneNumber == null || phoneNumber.length() != 10) {
      throw new AimsException(null, ErrorCodeList.INVALID_PHONE_NUMBER, HttpStatus.BAD_REQUEST);
    }
  }

  private void validateProvince() {
    if (province == null || ProvinceEnum.fromString(province).equals(ProvinceEnum.UNKNOWN)) {
      throw new AimsException(null, ErrorCodeList.INVALID_PROVINCE, HttpStatus.BAD_REQUEST);
    }
  }

  private void validateRushDelivery() {
    if (isOrderForRushDelivery && !ProvinceEnum.fromString(province).equals(ProvinceEnum.HANOI)) {
      throw new AimsException(null, ErrorCodeList.CITY_NOT_SUPPORT_RUSH_DELIVERY,
          HttpStatus.BAD_REQUEST);
    }
  }

  private Instant parseDeliveryTime() {
    return Optional.ofNullable(deliveryTime)
        .map(this::convertToInstant)
        .orElse(null);
  }

  private Instant convertToInstant(String time) {
    try {
      return Instant.parse(time);
    } catch (Exception e) {
      throw new AimsException(null, ErrorCodeList.INVALID_TIME, HttpStatus.BAD_REQUEST);
    }
  }
}