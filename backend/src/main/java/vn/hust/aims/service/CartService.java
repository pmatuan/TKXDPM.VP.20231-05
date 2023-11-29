package vn.hust.aims.service;

import org.springframework.stereotype.Service;
import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.exception.MediaNotAvailableException;

@Service
public class CartService {

  private Cart cart;

  public CartService() {
    this.cart = new Cart();
  }

  public Long getCartId(){
    return cart.getId();
  }

  public void addCartMedia(CartMedia cartMedia) {
    cart.getCartMediaList().add(cartMedia);
  }

  public void removeCartMedia(CartMedia cartMedia) {
    cart.getCartMediaList().remove(cartMedia);
  }

  public void emptyCart() {
    cart.getCartMediaList().clear();
  }

  public Double calculateSubtotal() {
    Double subtotal = 0.0;
    for (CartMedia cartMedia : cart.getCartMediaList()) {
      subtotal += cartMedia.getMedia().getPrice() * cartMedia.getQuantity();
    }
    return subtotal;
  }

  public void checkAvailabilityOfMedia() {
    for (CartMedia cartMedia : cart.getCartMediaList()) {
      Integer requiredQuantity = cartMedia.getQuantity();
      Integer availableQuantity = cartMedia.getMedia().getQuantityInStock();
      if (requiredQuantity > availableQuantity) {
        throw new MediaNotAvailableException(
            "Media " + cartMedia.getMedia().getTitle() + " is not available");
      }
    }
  }
}
