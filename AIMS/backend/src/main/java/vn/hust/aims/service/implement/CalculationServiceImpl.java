/*
  Functional cohesion: hầu hết các thành phần của lớp này đều hướng đến việc thực hiện một trách nhiệm chung rõ ràng là tính toán hóa đơn. Mỗi phương thức sử dụng kết quả của phương thức khác theo một quy trình.
*/

package vn.hust.aims.service.implement;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.entity.order.OrderMedia;
import vn.hust.aims.enumeration.ProvinceEnum;
import vn.hust.aims.service.CalculationService;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

  // data-coupling
  public Double calculateCartMediaSubtotal(List<CartMedia> cartMediaList) {
    return cartMediaList.stream()
        .mapToDouble(cartMedia -> cartMedia.getMedia().getPrice() * cartMedia.getQuantity())
        .sum();
  }

  // data-coupling
  public Double calculateOrderMediaSubtotal(List<OrderMedia> orderMediaList) {
    return orderMediaList.stream()
        .mapToDouble(orderMedia -> orderMedia.getMedia().getPrice() * orderMedia.getQuantity())
        .sum();
  }


  // data-coupling
  public Double calculateVAT(Double subtotal) {
    return subtotal / 10; // VAT is 10% of the subtotal
  }

  public Double calculateDeliveryFee(Order order) {

    final Double FREE_SHIP = 100000.0;

    if (order.getDeliveryInfo() == null) {
      return null;
    }

    String province = order.getDeliveryInfo().getProvince();

    Double maxWeight = order.getOrderMediaList().stream()
        .map(orderMedia -> orderMedia.getMedia().getWeight())
        .max(Double::compare)
        .orElse(0.0);

    Double originalDeliveryFee;

    if (order.getSubtotal() > FREE_SHIP) {
      originalDeliveryFee = 0.0;
    } else if (isInHanoi(province) || isInHCM(province)) {
      // data coupling
      originalDeliveryFee = calculateFeeInHanoiOrHCM(maxWeight);
    } else {
      // data coupling
      originalDeliveryFee = calculateFeeOutside(maxWeight);
    }

    if (order.getRushOrder() != null) {
      return originalDeliveryFee + calculateRushDeliveryFee(order.getOrderMediaList());
    }

    return originalDeliveryFee;
  }

  public Double calculateTotal(Double subtotal, Double VAT) {
    return subtotal + VAT;
  }

  public Double calculateTotal(Double subtotal, Double VAT, Double deliveryFee) {
    return subtotal + VAT + deliveryFee;
  }

  private Boolean isInHanoi(String province) {
    return province.equals(ProvinceEnum.HANOI.getStringValue());
  }

  private Boolean isInHCM(String province) {
    return province.equals(ProvinceEnum.HOCHIMINH.getStringValue());
  }

  private Double calculateFeeInHanoiOrHCM(Double maxWeight) {
    final Double BASE_FEE = 22000.0;
    final Double ADDITIONAL_WEIGHT_THRESHOLD = 3.0;
    final Double ADDITIONAL_WEIGHT_FEE_UNIT = 0.5;
    final Double ADDITIONAL_FEE_UNIT = 2500.0;

    if (maxWeight <= ADDITIONAL_WEIGHT_THRESHOLD) {
      return BASE_FEE;
    }

    Double additionalWeight = maxWeight - ADDITIONAL_WEIGHT_THRESHOLD;
    Double additionalFee =
        Math.ceil(additionalWeight / ADDITIONAL_WEIGHT_FEE_UNIT) * ADDITIONAL_FEE_UNIT;
    return BASE_FEE + additionalFee;
  }

  private Double calculateFeeOutside(Double maxWeight) {
    final Double BASE_FEE = 30000.0;
    final Double INITIAL_WEIGHT = 0.5;
    final Double ADDITIONAL_FEE_UNIT = 2500.0;

    if (maxWeight <= INITIAL_WEIGHT) {
      return BASE_FEE;
    }

    Double additionalWeight = maxWeight - INITIAL_WEIGHT;
    Double additionalFee = Math.ceil(additionalWeight / 0.5) * ADDITIONAL_FEE_UNIT;
    return BASE_FEE + additionalFee;
  }

  private Double calculateRushDeliveryFee(List<OrderMedia> orderMediaList){
    final Double BASE_RUSH_DELIVERY_FEE = 10000.0;

    List<OrderMedia> rushDeliveryItems = orderMediaList.stream()
        .filter(orderMedia -> orderMedia.getIsOrderForRushDelivery())
        .collect(Collectors.toList());

    Long totalRushDeliveryQuantity = rushDeliveryItems.stream()
        .mapToLong(OrderMedia::getQuantity)
        .sum();

    Double rushDeliveryFee = totalRushDeliveryQuantity * BASE_RUSH_DELIVERY_FEE;

    return rushDeliveryFee;
  }
}

// Design principle
// - SRP: Thoả mãn, vì lớp làm 1 nhiệm vụ là tính tiền
// - OCP: Thoả mãn, khi cần mở rộng có thể viết thêm phương thức vào mà không ảnh hưởng các phương thức khác
