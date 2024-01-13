package vn.hust.aims.service;

import vn.hust.aims.entity.order.DeliveryInfo;
import vn.hust.aims.service.dto.input.placeorder.UpdateDeliveryInfoInput;

public interface DeliveryInfoService {

  // Create a new delivery information
  DeliveryInfo createDeliveryInfo(UpdateDeliveryInfoInput input);
}
