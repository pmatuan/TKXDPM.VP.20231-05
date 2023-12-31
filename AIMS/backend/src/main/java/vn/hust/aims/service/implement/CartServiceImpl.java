package vn.hust.aims.service.implement;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.cart.Cart;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.exception.CartMediaNotFoundException;
import vn.hust.aims.exception.CartNotFoundException;
import vn.hust.aims.repository.cart.CartMediaRepository;
import vn.hust.aims.repository.cart.CartRepository;
import vn.hust.aims.service.CalculationService;
import vn.hust.aims.service.CartService;
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
import vn.hust.aims.service.MediaService;


// SOLID analysis
// Single responsibility: Lớp này chỉ có duy nhất một lý do để thay đổi là chức năng liên quan đến giỏ hàng
// Open close: Dễ thêm tính năng, chỉ cần thêm phương thức vào lớp
// Dependency Inversion: Không thỏa mãn vì đang phụ thuộc trực tiếp vào lớp cartRepository, cartMediaRepository, mediaService, calculationService
@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CartMediaRepository cartMediaRepository;
  private final MediaService mediaService;
  private final CalculationService calculationService;

  // data-coupling

  // Functional cohesion
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
    Double subtotal = calculationService.calculateCartMediaSubtotal(cart.getCartMediaList());
    Double VAT = calculationService.calculateVAT(subtotal);
    Double total = calculationService.calculateTotal(subtotal, VAT);

    return GetCartOutput.from(cart, subtotal, VAT, total);
  }

  // data coupling
  // Sử dụng lớp dto DeleteCartInput
  // Nhận đủ dữ liệu để thực thi xóa cart
  public DeleteCartOutput deleteCart(DeleteCartInput input) {

    if (!cartRepository.existsById(input.getCartId())) {
      throw new CartNotFoundException();
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
    Media media = mediaService.getMediaById(input.getMediaId());

    mediaService.validateQuantityInStock(media, input.getQuantity());

    Optional<CartMedia> existingCartMedia = findCartMediaInCart(cart, input.getMediaId());

    if (existingCartMedia.isPresent()) {
      existingCartMedia.get()
          .setQuantity(existingCartMedia.get().getQuantity() + input.getQuantity());
    } else {
      addCartMedia(cart, media, input.getQuantity());
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

    mediaService.validateQuantityInStock(cartMedia.getMedia(), input.getQuantity());
    updateCartMediaQuantity(cart, cartMedia, input.getQuantity());

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
  public Cart getCartById(String cartId) {
    return cartRepository.findById(cartId)
        .orElseThrow(() -> new CartNotFoundException());
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
  private void addCartMedia(Cart cart, Media media, Integer quantity) {
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
  private CartMedia findCartMediaById(Cart cart, Long cartMediaId) {
    return cart.getCartMediaList().stream()
        .filter(cartMedia -> cartMedia.getId().equals(cartMediaId))
        .findFirst()
        .orElseThrow(() -> new CartMediaNotFoundException());
  }

  // data-coupling
  // Chỉ dùng đủ dữ liệu để thực thi chức năng xóa media trong cart
  private void deleteCartMedia(CartMedia cartMedia, Cart cart) {
    cartMediaRepository.delete(cartMedia);
    cart.removeCartMedia(cartMedia);
  }

  // data-coupling
  // Chỉ dùng đủ dữ liệu để thực thi chức năng cập nhật số lượng media trong cart
  private void updateCartMediaQuantity(Cart cart, CartMedia cartMedia, Integer quantity) {
    if (quantity == 0){
      deleteCartMedia(cartMedia, cart);
    }
    else {
      cartMedia.setQuantity(quantity);
      cartMediaRepository.save(cartMedia);
    }
  }

}

// Design principle
// - SRP: Thoả mãn, vì lớp làm 1 nhiệm vụ liên quan đến cart
// - OCP: Thoả mãn, khi cần mở rộng có thể viết thêm phương thức vào mà không ảnh hưởng các phương thức khác