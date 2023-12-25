package vn.hust.aims.controller.dto.request.placeorder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.entity.order.OrderMedia;
import vn.hust.aims.exception.InvalidEmailException;
import vn.hust.aims.exception.InvalidPhoneNumberException;
import vn.hust.aims.exception.NotSupportRushDeliveryException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateDeliveryInfoRequestTest {

  @Mock
  private Media media1;

  @Mock
  private Media media2;

  @Mock
  private OrderMedia orderMedia1;

  @Mock
  private OrderMedia orderMedia2;

  @Mock
  private Order mockOrder;

  @InjectMocks
  private UpdateDeliveryInfoRequest updateDeliveryInfoRequest;

  @Test
  void testValidateRequest_validEmailFormat() {
    updateDeliveryInfoRequest.setEmail("valid.email@gmail.com");
    updateDeliveryInfoRequest.setPhoneNumber("0819235067");
    updateDeliveryInfoRequest.setProvince("Hà Nội"); // should remove
    updateDeliveryInfoRequest.setIsOrderForRushDelivery(true);

    assertDoesNotThrow(() -> updateDeliveryInfoRequest.toInput("orderId"));
  }

  @Test
  void testValidateRequest_invalidEmailFormat() {
    updateDeliveryInfoRequest.setEmail("valid.emailgmail.com");
    updateDeliveryInfoRequest.setPhoneNumber("0819235067");
    updateDeliveryInfoRequest.setProvince("Hà Nội"); // should remove
    updateDeliveryInfoRequest.setIsOrderForRushDelivery(true);

    assertThrows(InvalidEmailException.class, () -> updateDeliveryInfoRequest.toInput("orderId"));
  }

  @Test
  void testValidateRequest_validPhoneNumberFormat() {
    updateDeliveryInfoRequest.setEmail("valid.email@gmail.com");
    updateDeliveryInfoRequest.setPhoneNumber("0819235067");
    updateDeliveryInfoRequest.setProvince("Hà Nội"); // should remove
    updateDeliveryInfoRequest.setIsOrderForRushDelivery(true);

    assertDoesNotThrow(() -> updateDeliveryInfoRequest.toInput("orderId"));
  }

  @Test
  void testValidateRequest_invalidPhoneNumberFormat() {
    updateDeliveryInfoRequest.setEmail("valid.email@gmail.com");
    updateDeliveryInfoRequest.setPhoneNumber("081923567");
    updateDeliveryInfoRequest.setProvince("Hà Nội"); // should remove
    updateDeliveryInfoRequest.setIsOrderForRushDelivery(true);

    assertThrows(InvalidPhoneNumberException.class, () -> updateDeliveryInfoRequest.toInput("orderId"));
  }

  @Test
  void testValidateRequest_deliveryInfoSupportRushOrder() {
    updateDeliveryInfoRequest.setEmail("valid.email@gmail.com");
    updateDeliveryInfoRequest.setPhoneNumber("0819235067");
    updateDeliveryInfoRequest.setProvince("Hà Nội"); // should remove
    updateDeliveryInfoRequest.setIsOrderForRushDelivery(true);

    assertDoesNotThrow(() -> updateDeliveryInfoRequest.toInput("orderId"));
  }

  @Test
  void testValidateRequest_deliveryInfoNotSupportRushOrder() {
    updateDeliveryInfoRequest.setEmail("valid.email@gmail.com");
    updateDeliveryInfoRequest.setPhoneNumber("0819235067");
    updateDeliveryInfoRequest.setProvince("Tuyên Quang"); // should remove
    updateDeliveryInfoRequest.setIsOrderForRushDelivery(true);

    assertThrows(NotSupportRushDeliveryException.class, () -> updateDeliveryInfoRequest.toInput("orderId"));
  }

  @Test
  void testValidateRequest_hasProductSupportRushDelivery() {
    updateDeliveryInfoRequest.setEmail("valid.email@gmail.com");
    updateDeliveryInfoRequest.setPhoneNumber("0819235067");
    updateDeliveryInfoRequest.setProvince("Hà Nội");

    when(media1.getIsAbleToRushDelivery()).thenReturn(true);
    when(media2.getIsAbleToRushDelivery()).thenReturn(false);

    when(orderMedia1.getMedia()).thenReturn(media1);
    when(orderMedia2.getMedia()).thenReturn(media2);

    updateDeliveryInfoRequest.setIsOrderForRushDelivery(true);

    assertDoesNotThrow(() -> updateDeliveryInfoRequest.toInput("orderId"));
  }

  @Test
  void testValidateRequest_noProductSupportRushDelivery() {
    updateDeliveryInfoRequest.setEmail("valid.email@gmail.com");
    updateDeliveryInfoRequest.setPhoneNumber("0819235067");
    updateDeliveryInfoRequest.setProvince("Hà Nội");

    when(media1.getIsAbleToRushDelivery()).thenReturn(false);
    when(media2.getIsAbleToRushDelivery()).thenReturn(false);

    when(orderMedia1.getMedia()).thenReturn(media1);
    when(orderMedia2.getMedia()).thenReturn(media2);

    updateDeliveryInfoRequest.setIsOrderForRushDelivery(true);

    assertThrows(NotSupportRushDeliveryException.class, () -> updateDeliveryInfoRequest.toInput("orderId"));
  }

}
