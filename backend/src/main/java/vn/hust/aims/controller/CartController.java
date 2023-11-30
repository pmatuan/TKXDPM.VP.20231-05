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
import vn.hust.aims.controller.dto.response.cart.GetCartResponse;
import vn.hust.aims.controller.dto.response.cart.UpdateMediaInCartResponse;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.CartService;
import vn.hust.aims.service.dto.input.cart.DeleteCartInput;
import vn.hust.aims.service.dto.input.cart.GetCartInput;
import vn.hust.aims.service.dto.output.cart.AddMediaToCartOutput;
import vn.hust.aims.service.dto.output.cart.CreateCartOutput;
import vn.hust.aims.service.dto.output.cart.DeleteCartOutput;
import vn.hust.aims.service.dto.output.cart.GetCartOutput;
import vn.hust.aims.service.dto.output.cart.UpdateMediaInCartOutput;
import vn.hust.aims.utils.ResponseUtil;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

  private final CartService cartService;

  @GetMapping("")
  public ResponseEntity<AimsCommonResponse<Object>> createCart() {

    CreateCartOutput output = cartService.createCart();

    return ResponseUtil.toSuccessCommonResponse(
        CreateCartResponse.from(output)
    );
  }

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

  @PostMapping("/{cartId}/cart-media")
  public ResponseEntity<AimsCommonResponse<Object>> addMediaToCart(
      @PathVariable String cartId, @RequestBody AddMediaToCartRequest request) {

    AddMediaToCartOutput output = cartService.addMediaToCart(request.toInput(cartId));

    return ResponseUtil.toSuccessCommonResponse(
        AddMediaToCartResponse.from(output)
    );
  }

  @PutMapping("/{cartId}/cart-media/{cartMediaId}")
  public ResponseEntity<AimsCommonResponse<Object>> updateMediaInCart(
      @PathVariable Long cartMediaId, @RequestBody UpdateMediaInCartRequest request
  ) {

    UpdateMediaInCartOutput output = cartService.updateMediaInCart(request.toInput(cartMediaId));

    return ResponseUtil.toSuccessCommonResponse(
        UpdateMediaInCartResponse.from(output)
    );
  }

}
