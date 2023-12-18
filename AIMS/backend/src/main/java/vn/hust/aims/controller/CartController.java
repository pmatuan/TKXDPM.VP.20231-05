package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.controller.dto.request.cart.AddMediaToCartRequest;
import vn.hust.aims.controller.dto.request.cart.UpdateMediaInCartRequest;
import vn.hust.aims.controller.dto.response.cart.AddMediaToCartResponse;
import vn.hust.aims.controller.dto.response.cart.CreateCartResponse;
import vn.hust.aims.controller.dto.response.cart.DeleteCartResponse;
import vn.hust.aims.controller.dto.response.cart.DeleteMediaInCartResponse;
import vn.hust.aims.controller.dto.response.cart.GetCartResponse;
import vn.hust.aims.controller.dto.response.cart.UpdateMediaInCartResponse;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.CartService;
import vn.hust.aims.service.dto.input.cart.DeleteCartInput;
import vn.hust.aims.service.dto.input.cart.DeleteMediaInCartInput;
import vn.hust.aims.service.dto.input.cart.GetCartInput;
import vn.hust.aims.service.dto.output.cart.AddMediaToCartOutput;
import vn.hust.aims.service.dto.output.cart.CreateCartOutput;
import vn.hust.aims.service.dto.output.cart.DeleteCartOutput;
import vn.hust.aims.service.dto.output.cart.DeleteMediaInCartOutput;
import vn.hust.aims.service.dto.output.cart.GetCartOutput;
import vn.hust.aims.service.dto.output.cart.UpdateMediaInCartOutput;
import vn.hust.aims.utils.ResponseUtil;


// CartController

// Data coupling
// Lớp CartController nhận dữ liệu từ Client thông qua các lớp dto request của Cart
// Mỗi phương thức (nếu có) sẽ có dto request và response riêng
// CartController sử dụng CartService

// Functional Cohesion

// SOLID
// Single responsibility: Lớp này chỉ có duy nhất một lý do để thay đổi là chức năng liên quan đến giỏ hàng
// Open close: Dễ thêm tính năng, chỉ cần thêm phương thức vào lớp
// Dependency Inversion: Không thỏa mãn vì đang phụ thuộc trực tiếp vào lớp CartService ?
@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

  private final CartService cartService;

  // Data coupling
  @GetMapping("")
  public ResponseEntity<AimsCommonResponse<Object>> createCart() {
    CreateCartOutput output = cartService.createCart();

    return ResponseUtil.toSuccessCommonResponse(
        CreateCartResponse.from(output)
    );
  }

  // Data-coupling
  @GetMapping("/{cartId}")
  public ResponseEntity<AimsCommonResponse<Object>> getCart(@PathVariable String cartId) {

    GetCartOutput output = cartService.getCart(
        GetCartInput.builder()
            .cartId(cartId)
            .build()
    );

    return ResponseUtil.toSuccessCommonResponse(
        GetCartResponse.from(output)
    );
  }

  // Data-coupling
  @DeleteMapping("/{cartId}")
  public ResponseEntity<AimsCommonResponse<Object>> deleteCart(@PathVariable String cartId) {

    DeleteCartOutput output = cartService.deleteCart(
        DeleteCartInput.builder()
            .cartId(cartId)
            .build()
    );

    return ResponseUtil.toSuccessCommonResponse(
        DeleteCartResponse.from(output)
    );
  }

  // Data-coupling
  @PostMapping("/{cartId}/cart-media")
  public ResponseEntity<AimsCommonResponse<Object>> addMediaToCart(
      @PathVariable String cartId, @RequestBody AddMediaToCartRequest request) {

    AddMediaToCartOutput output = cartService.addMediaToCart(request.toInput(cartId));

    return ResponseUtil.toSuccessCommonResponse(
        AddMediaToCartResponse.from(output)
    );
  }

  // Data-coupling
  @PutMapping("/{cartId}/cart-media/{cartMediaId}")
  public ResponseEntity<AimsCommonResponse<Object>> updateMediaInCart(
      @PathVariable String cartId, @PathVariable Long cartMediaId, @RequestBody UpdateMediaInCartRequest request) {

    UpdateMediaInCartOutput output = cartService.updateMediaInCart(request.toInput(cartId, cartMediaId));

    return ResponseUtil.toSuccessCommonResponse(
        UpdateMediaInCartResponse.from(output)
    );
  }

  // Data-coupling
  @DeleteMapping("/{cartId}/cart-media/{cartMediaId}")
  public ResponseEntity<AimsCommonResponse<Object>> deleteMediaInCart(
      @PathVariable String cartId, @PathVariable Long cartMediaId
  ) {

    DeleteMediaInCartOutput output = cartService.deleteMediaInCart(
        DeleteMediaInCartInput.builder()
            .cartId(cartId)
            .cartMediaId(cartMediaId)
            .build()
    );

    return ResponseUtil.toSuccessCommonResponse(
        DeleteMediaInCartResponse.from(output)
    );
  }

}
