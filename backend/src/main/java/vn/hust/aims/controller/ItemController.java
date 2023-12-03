package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.service.ItemService;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController {
  private final ItemService itemService;
  // TODO: CRUD sản phẩm, tìm kiếm sản phẩm, sắp xếp sản phẩm, xem chi tiết sản phẩm (gộp với CRUD sản phẩm)


}
