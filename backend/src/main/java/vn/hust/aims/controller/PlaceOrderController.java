package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.service.PlaceOrderService;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/placeorder")
public class PlaceOrderController {
  private final PlaceOrderService placeOrderService;
  // TODO: Đặt hàng, đặt hàng nhanh
}
