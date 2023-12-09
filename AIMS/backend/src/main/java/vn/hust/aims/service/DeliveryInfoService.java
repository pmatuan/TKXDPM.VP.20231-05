package vn.hust.aims.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.order.DeliveryInfo;
import vn.hust.aims.repository.order.DeliveryInfoRepository;
import vn.hust.aims.service.dto.input.placeorder.UpdateDeliveryInfoInput;

@Service
@AllArgsConstructor
public class DeliveryInfoService {
  private final DeliveryInfoRepository deliveryInfoRepository;
  public DeliveryInfo createDeliveryInfo(UpdateDeliveryInfoInput input) {

    DeliveryInfo deliveryInfo = DeliveryInfo.builder()
        .customerName(input.getCustomerName())
        .email(input.getEmail())
        .phoneNumber(input.getPhoneNumber())
        .city(input.getProvince().getStringValue())
        .address(input.getAddress())
        .build();

    deliveryInfoRepository.save(deliveryInfo);

    return deliveryInfo;
  }
}
