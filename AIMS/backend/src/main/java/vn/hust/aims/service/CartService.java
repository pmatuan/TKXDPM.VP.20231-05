package vn.hust.aims.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.exception.AimsException;
import vn.hust.aims.exception.ErrorCodeList;
import vn.hust.aims.repository.cart.CartMediaRepository;
import vn.hust.aims.repository.cart.CartRepository;
import vn.hust.aims.repository.media.MediaRepository;
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

@Service
@AllArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final CartMediaRepository cartMediaRepository;
  private final MediaRepository mediaRepository;

  // data-coupling
  public CreateCartOutput createCart() {

    String cartId = UUID.randomUUID().toString();

    Cart cart = Cart.builder()
        .id(cartId)
        .cartMediaList(new ArrayList<>())
        .build();
    cartRepository.save(cart);

    return CreateCartOutput.from(cartId);
  }

  // data coupling
  // Sử dụng lớp dto GetCartInput
  // Nhận đủ dữ liệu để thực thi lấy cart
  public GetCartOutput getCart(GetCartInput input) {

    Cart cart = getCartById(input.getCartId());

    return GetCartOutput.from(cart);
  }

  // data coupling
  // Sử dụng lớp dto DeleteCartInput
  // Nhận đủ dữ liệu để thực thi xóa cart
  public DeleteCartOutput deleteCart(DeleteCartInput input) {

    if (!cartRepository.existsById(input.getCartId())) {
      throw new AimsException(null, ErrorCodeList.CART_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    cartRepository.deleteById(input.getCartId());

    return DeleteCartOutput.from("Cart " + input.getCartId() + " deleted successfully");
  }

  // data coupling
  // Sử dụng lớp dto AddMediaToCartInput
  // Nhận đủ dữ liệu để thực thi thêm media vào cart
  // Không thay đổi hành vi phụ thuộc vào input
  public AddMediaToCartOutput addMediaToCart(AddMediaToCartInput input) {
    Cart cart = getCartById(input.getCartId());
    Media media = getMediaById(input.getMediaId());

    validateQuantityInStock(media, input.getQuantity());

    Optional<CartMedia> existingCartMedia = findCartMediaInCart(cart, input.getMediaId());

    if (existingCartMedia.isPresent()) {
      existingCartMedia.get()
          .setQuantity(existingCartMedia.get().getQuantity() + input.getQuantity());
    } else {
      addNewCartMediaToCart(cart, media, input.getQuantity());
    }

    cartRepository.save(cart);

    return AddMediaToCartOutput.from(
        "Add media " + input.getMediaId() + " to cart " + input.getCartId() + " successfully");
  }

  // data coupling
  // Sử dụng lớp dto UpdateMediaToCartInput
  // Nhận đủ dữ liệu để thực thi cập nhật media trong cart
  // Không thay đổi hành vi phụ thuộc vào input
  public UpdateMediaInCartOutput updateMediaInCart(UpdateMediaInCartInput input) {

    Cart cart = getCartById(input.getCartId());
    CartMedia cartMedia = findCartMediaById(cart, input.getCartMediaId());

    validateQuantityInStock(cartMedia.getMedia(), input.getQuantity());
    updateCartMediaQuantity(cartMedia, input.getQuantity());

    return UpdateMediaInCartOutput.from(
        "Update quantity cart media " + input.getCartMediaId() + " to " + input.getQuantity() +
            " successfully");
  }

  // data coupling
  // Sử dụng lớp dto DeleteMediaToCartInput
  // Nhận đủ dữ liệu để thực thi xóa media trong cart
  // Không thay đổi hành vi phụ thuộc vào input
  public DeleteMediaInCartOutput deleteMediaInCart(DeleteMediaInCartInput input) {

    Cart cart = getCartById(input.getCartId());
    CartMedia cartMedia = findCartMediaById(cart, input.getCartMediaId());

    deleteCartMedia(cartMedia, cart);

    return DeleteMediaInCartOutput.from(
        "Deleted cart media " + input.getCartMediaId() + " successfully");
  }

  // data-coupling
  // Chỉ dùng đủ dữ liệu để tìm cart - cartId
  private Cart getCartById(String cartId) {
    return cartRepository.findById(cartId)
        .orElseThrow(
            () -> new AimsException(null, ErrorCodeList.CART_NOT_FOUND, HttpStatus.BAD_REQUEST));
  }

  // data-coupling
  // Chỉ duùng đủ dữ liệu để tìm media - mediaId
  private Media getMediaById(Long mediaId) {
    return mediaRepository.findById(mediaId)
        .orElseThrow(
            () -> new AimsException(null, ErrorCodeList.MEDIA_NOT_FOUND, HttpStatus.BAD_REQUEST));
  }

  // data-coupling
  // Chỉ dùng đủ dữ liệu để tìm cart media - chỉ truyền vào media và số quantity cần có
  private void validateQuantityInStock(Media media, Integer requestedQuantity) {
    if (media.getQuantityInStock() < requestedQuantity) {
      throw new AimsException(null, ErrorCodeList.QUANTITY_NOT_ENOUGH, HttpStatus.BAD_REQUEST);
    }
  }

  // data-coupling
  // Chỉ dùng đủ dữ liệu để tìm cart media - chỉ truyền vào ID
  private Optional<CartMedia> findCartMediaInCart(Cart cart, Long mediaId) {
    return cart.getCartMediaList().stream()
        .filter(cartMedia -> cartMedia.getMedia().getId().equals(mediaId))
        .findFirst();
  }

  // data-coupling
  // Chỉ dùng đủ dữ liệu để thực thi chức năng thêm media vào cart
  private void addNewCartMediaToCart(Cart cart, Media media, Integer quantity) {
    CartMedia cartMedia = CartMedia.builder()
        .cart(cart)
        .media(media)
        .quantity(quantity)
        .build();
    cart.addCartMedia(cartMedia);
    cartMediaRepository.save(cartMedia);
  }

  // data-coupling
  // Chỉ dùng đủ dữ liệu để tìm cart media - chỉ truyền vào ID
  private CartMedia findCartMediaById(Cart cart, Long cartMediaId){
    return cart.getCartMediaList().stream()
        .filter(cartMedia -> cartMedia.getId().equals(cartMediaId))
        .findFirst()
        .orElseThrow(() -> new AimsException(null, ErrorCodeList.CART_MEDIA_NOT_FOUND, HttpStatus.BAD_REQUEST));
  }

  // data-coupling
  // Chỉ dùng đủ dữ liệu để thực thi chức năng xóa media trong cart
  private void deleteCartMedia(CartMedia cartMedia, Cart cart){
    cartMediaRepository.delete(cartMedia);
    cart.removeCartMedia(cartMedia);
  }

  // data-coupling
  // Chỉ dùng đủ dữ liệu để thực thi chức năng cập nhật số lượng media trong cart
  private void updateCartMediaQuantity(CartMedia cartMedia, Integer quantity){
    cartMedia.setQuantity(quantity);
    cartMediaRepository.save(cartMedia);
  }

}
