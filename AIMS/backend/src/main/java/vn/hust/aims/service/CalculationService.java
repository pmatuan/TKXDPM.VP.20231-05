package vn.hust.aims.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.entity.order.OrderMedia;
import vn.hust.aims.enumeration.ProvinceEnum;

@Service
@RequiredArgsConstructor
public class CalculationService {

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

    String city = order.getDeliveryInfo().getCity();

    Double rushDeliveryFee = calculateRushDeliveryFee(order.getOrderMediaList());

    if (order.getSubtotal() > FREE_SHIP) {
      return isInHanoi(city) ? rushDeliveryFee : 0.0;
    }

    Double maxWeight = order.getOrderMediaList().stream()
        .map(orderMedia -> orderMedia.getMedia().getWeight())
        .max(Double::compare)
        .orElse(0.0);

    if (isInHanoi(city)) {
      // data coupling
      return calculateFeeInHanoiOrHCM(maxWeight) + rushDeliveryFee;
    } else if (isInHCM(city)) {
      // data coupling
      return calculateFeeInHanoiOrHCM(maxWeight);
    } else {
      // data coupling
      return calculateFeeOutside(maxWeight);
    }
  }

  public Double calculateTotal(Double subtotal, Double VAT) {
    return subtotal + VAT;
  }

  public Double calculateTotal(Double subtotal, Double VAT, Double deliveryFee) {
    return subtotal + VAT + deliveryFee;
  }

  private Boolean isInHanoi(String city) {
    return city.equals(ProvinceEnum.HANOI.getStringValue());
  }

  private Boolean isInHCM(String city) {
    return city.equals(ProvinceEnum.HOCHIMINH.getStringValue());
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
