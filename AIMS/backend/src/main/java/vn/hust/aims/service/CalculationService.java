package vn.hust.aims.service;

import java.util.List;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.entity.order.OrderMedia;

public interface CalculationService {

  // Calculate subtotal for cart media
  Double calculateCartMediaSubtotal(List<CartMedia> cartMediaList);

  // Calculate subtotal for order media
  Double calculateOrderMediaSubtotal(List<OrderMedia> orderMediaList);

  // Calculate VAT based on subtotal
  Double calculateVAT(Double subtotal);

  // Calculate delivery fee for an order
  Double calculateDeliveryFee(Order order);

  // Calculate total cost with VAT and delivery fee
  Double calculateTotal(Double subtotal, Double VAT);

  // Calculate total cost with VAT, delivery fee, and rush delivery fee
  Double calculateTotal(Double subtotal, Double VAT, Double deliveryFee);
}
