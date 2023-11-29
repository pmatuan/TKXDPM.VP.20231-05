package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.service.CartService;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
  private final CartService cartService;
  // TODO: CRUD giỏ hàng

}
