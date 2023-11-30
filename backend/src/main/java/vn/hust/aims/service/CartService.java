package vn.hust.aims.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.exception.AimsException;
import vn.hust.aims.exception.ErrorCodeList;
import vn.hust.aims.exception.MediaNotAvailableException;
import vn.hust.aims.repository.cart.CartMediaRepository;
import vn.hust.aims.repository.cart.CartRepository;
import vn.hust.aims.repository.media.MediaRepository;
import vn.hust.aims.service.dto.input.cart.AddMediaToCartInput;
import vn.hust.aims.service.dto.input.cart.DeleteCartInput;
import vn.hust.aims.service.dto.input.cart.GetCartInput;
import vn.hust.aims.service.dto.output.cart.AddMediaToCartOutput;
import vn.hust.aims.service.dto.output.cart.CreateCartOutput;
import vn.hust.aims.service.dto.output.cart.DeleteCartOutput;
import vn.hust.aims.service.dto.output.cart.GetCartOutput;

@Service
@AllArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final CartMediaRepository cartMediaRepository;
  private final MediaRepository mediaRepository;

  public CreateCartOutput createCart() {

    String cartId = UUID.randomUUID().toString();

    Cart cart = Cart.builder()
        .id(cartId)
        .cartMediaList(new ArrayList<>())
        .build();
    cartRepository.save(cart);

    return CreateCartOutput.from(cartId);
  }

  public GetCartOutput getCart(GetCartInput input) {

    Cart cart = cartRepository.findById(input.getCartId())
        .orElseThrow(
            () -> new AimsException(null, ErrorCodeList.CART_NOT_FOUND, HttpStatus.BAD_REQUEST));

    return GetCartOutput.from(cart);
  }

  public DeleteCartOutput deleteCart(DeleteCartInput input) {

    if (!cartRepository.existsById(input.getCartId())) {
      throw new AimsException(null, ErrorCodeList.CART_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    cartRepository.deleteById(input.getCartId());

    return DeleteCartOutput.from("Cart " + input.getCartId() + " deleted successfully");
  }

  public AddMediaToCartOutput addMediaToCart(AddMediaToCartInput input) {

    Cart cart = cartRepository.findById(input.getCartId())
        .orElseThrow(
            () -> new AimsException(null, ErrorCodeList.CART_NOT_FOUND, HttpStatus.BAD_REQUEST));

    Media media = mediaRepository.findById(input.getMediaId())
        .orElseThrow(
            () -> new AimsException(null, ErrorCodeList.MEDIA_NOT_FOUND, HttpStatus.BAD_REQUEST));

    if (media.getQuantityInStock() < input.getQuantity()){
      throw new AimsException(null, ErrorCodeList.QUANTITY_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
    }

    CartMedia cartMedia = CartMedia.builder()
        .cart(cart)
        .media(media)
        .quantity(input.getQuantity())
        .build();
    cart.getCartMediaList().add(cartMedia);

    cartRepository.save(cart);
    cartMediaRepository.save(cartMedia);

    return AddMediaToCartOutput.from("Add media " + input.getMediaId() + " to cart " + input.getCartId() + " successfully");
  }

}
