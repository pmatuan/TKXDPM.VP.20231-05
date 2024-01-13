package vn.hust.aims.service;

import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.service.dto.input.cart.AddMediaToCartInput;
import vn.hust.aims.service.dto.input.cart.DeleteCartInput;
import vn.hust.aims.service.dto.input.cart.DeleteMediaInCartInput;
import vn.hust.aims.service.dto.input.cart.GetCartInput;
import vn.hust.aims.service.dto.input.cart.UpdateMediaInCartInput;
import vn.hust.aims.service.dto.output.cart.AddMediaToCartOutput;
import vn.hust.aims.service.dto.output.cart.CreateCartOutput;
import vn.hust.aims.service.dto.output.cart.DeleteCartOutput;
import vn.hust.aims.service.dto.output.cart.DeleteMediaInCartOutput;
import vn.hust.aims.service.dto.output.cart.GetCartOutput;
import vn.hust.aims.service.dto.output.cart.UpdateMediaInCartOutput;

public interface CartService {

  // Create a new cart
  CreateCartOutput createCart();

  // Get cart by id
  Cart getCartById(String cartId);

  // Get cart information
  GetCartOutput getCart(GetCartInput input);

  // Delete a cart
  DeleteCartOutput deleteCart(DeleteCartInput input);

  // Add media to cart
  AddMediaToCartOutput addMediaToCart(AddMediaToCartInput input);

  // Update media in cart
  UpdateMediaInCartOutput updateMediaInCart(UpdateMediaInCartInput input);

  // Delete media from cart
  DeleteMediaInCartOutput deleteMediaInCart(DeleteMediaInCartInput input);
}
