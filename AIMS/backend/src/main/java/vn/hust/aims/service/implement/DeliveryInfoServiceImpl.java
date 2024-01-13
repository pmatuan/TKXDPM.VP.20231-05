package vn.hust.aims.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.order.DeliveryInfo;
import vn.hust.aims.repository.order.DeliveryInfoRepository;
import vn.hust.aims.service.DeliveryInfoService;
import vn.hust.aims.service.dto.input.placeorder.UpdateDeliveryInfoInput;

@Service
@AllArgsConstructor
public class DeliveryInfoServiceImpl implements DeliveryInfoService {
  private final DeliveryInfoRepository deliveryInfoRepository;
  public DeliveryInfo createDeliveryInfo(UpdateDeliveryInfoInput input) {

    DeliveryInfo deliveryInfo = DeliveryInfo.builder()
        .customerName(input.getCustomerName())
        .email(input.getEmail())
        .phoneNumber(input.getPhoneNumber())
        .province(input.getProvince().getStringValue())
        .address(input.getAddress())
        .build();

    deliveryInfoRepository.save(deliveryInfo);

    return deliveryInfo;
  }
}

// Design principle
// - SRP: Thoả mãn, vì lớp làm 1 nhiệm vụ
// - OCP: Thoả mãn, khi cần mở rộng có thể viết thêm phương thức vào mà không ảnh hưởng các phương thức khác