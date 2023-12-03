package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.service.OrderService;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
  private final OrderService orderService;
  // TODO: Xem chi tiết đơn hàng, xét duyệt đơn hàng, huỷ đơn hàng, xem lịch sử mua hàng
}
