package vn.hust.aims.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.entity.order.DeliveryInfo;
import vn.hust.aims.entity.order.Order;
import vn.hust.aims.entity.order.OrderMedia;
import vn.hust.aims.enumeration.ProvinceEnum;
import vn.hust.aims.service.CalculationService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

  @Mock
  private Media media1;

  @Mock
  private Media media2;

  @Mock
  private CartMedia cartMedia1;

  @Mock
  private CartMedia cartMedia2;

  @Mock
  private OrderMedia orderMedia1;

  @Mock
  private OrderMedia orderMedia2;

  @Mock
  private Order mockOrder;

  @InjectMocks
  private CalculationService calculationService;

  @Test
  void testCalculateCartMediaSubtotal() {
    // Arrange
    when(cartMedia1.getMedia()).thenReturn(media1);
    when(media1.getPrice()).thenReturn(10.0);
    when(cartMedia1.getQuantity()).thenReturn(2);

    when(cartMedia2.getMedia()).thenReturn(media2);
    when(media2.getPrice()).thenReturn(15.0);
    when(cartMedia2.getQuantity()).thenReturn(3);

    List<CartMedia> cartMediaList = Arrays.asList(cartMedia1, cartMedia2);

    double result = calculationService.calculateCartMediaSubtotal(cartMediaList);

    assertEquals(10.0 * 2 + 15.0 * 3, result);
  }

  @Test
  void testCalculateOrderMediaSubtotal() {
    // Arrange
    when(orderMedia1.getMedia()).thenReturn(media1);
    when(media1.getPrice()).thenReturn(10.0);
    when(orderMedia1.getQuantity()).thenReturn(2);

    when(orderMedia2.getMedia()).thenReturn(media2);
    when(media2.getPrice()).thenReturn(15.0);
    when(orderMedia2.getQuantity()).thenReturn(3);

    List<OrderMedia> orderMediaList = Arrays.asList(orderMedia1, orderMedia2);

    double result = calculationService.calculateOrderMediaSubtotal(orderMediaList);

    assertEquals(10.0 * 2 + 15.0 * 3, result);
  }

  @Test
  void testCalculateDeliveryFee_totalOver100K() {
    when(orderMedia1.getIsOrderForRushDelivery()).thenReturn(false);
    when(orderMedia2.getIsOrderForRushDelivery()).thenReturn(false);

    List<OrderMedia> lstOrderMedia = Arrays.asList(orderMedia1, orderMedia2);

    String danangCity = ProvinceEnum.DANANG.getStringValue();
    DeliveryInfo mockDeliveryInfo = mock(DeliveryInfo.class);

    when(mockDeliveryInfo.getCity()).thenReturn(danangCity);
    when(mockOrder.getDeliveryInfo()).thenReturn(mockDeliveryInfo);
    when(mockOrder.getOrderMediaList()).thenReturn(lstOrderMedia);
    when(mockOrder.getSubtotal()).thenReturn(120000.0);

    Double result = calculationService.calculateDeliveryFee(mockOrder);

    assertEquals(0.0, result);
  }

  @Test
  void testCalculateDeliveryFee_totalUnder100KAndInHanoiOrHCMC() {
    when(media1.getWeight()).thenReturn(0.5);
    when(media2.getWeight()).thenReturn(0.7);

    when(orderMedia1.getMedia()).thenReturn(media1);
    when(orderMedia1.getIsOrderForRushDelivery()).thenReturn(false);

    when(orderMedia2.getMedia()).thenReturn(media2);
    when(orderMedia2.getIsOrderForRushDelivery()).thenReturn(false);

    List<OrderMedia> lstOrderMedia = Arrays.asList(orderMedia1, orderMedia2);

    String hanoiCity = ProvinceEnum.HANOI.getStringValue();
    DeliveryInfo mockDeliveryInfo = mock(DeliveryInfo.class);
    when(mockDeliveryInfo.getCity()).thenReturn(hanoiCity);

    when(mockOrder.getDeliveryInfo()).thenReturn(mockDeliveryInfo);
    when(mockOrder.getOrderMediaList()).thenReturn(lstOrderMedia);
    when(mockOrder.getSubtotal()).thenReturn(90000.0);

    Double result = calculationService.calculateDeliveryFee(mockOrder);

    assertEquals(22000.0, result);
  }

  @Test
  void testCalculateDeliveryFee_totalUnder100KAndNotInHanoiOrHCMC() {
    when(media1.getWeight()).thenReturn(0.5);
    when(media2.getWeight()).thenReturn(0.7);

    when(orderMedia1.getMedia()).thenReturn(media1);
    when(orderMedia1.getIsOrderForRushDelivery()).thenReturn(false);

    when(orderMedia2.getMedia()).thenReturn(media2);
    when(orderMedia2.getIsOrderForRushDelivery()).thenReturn(false);

    List<OrderMedia> lstOrderMedia = Arrays.asList(orderMedia1, orderMedia2);

    String haiphongCity = ProvinceEnum.HAIPHONG.getStringValue();
    DeliveryInfo mockDeliveryInfo = mock(DeliveryInfo.class);
    when(mockDeliveryInfo.getCity()).thenReturn(haiphongCity);

    when(mockOrder.getDeliveryInfo()).thenReturn(mockDeliveryInfo);
    when(mockOrder.getOrderMediaList()).thenReturn(lstOrderMedia);
    when(mockOrder.getSubtotal()).thenReturn(90000.0);

    Double result = calculationService.calculateDeliveryFee(mockOrder);

    assertEquals(32500.0, result);
  }

  // Thảo luận lại về công thức tính phí vận chuyển khi đơn hàng giá trị trên 100k và được đặt giao hàng nhanh
  @Test
  void testCalculateRushDeliveryFee_someProductsSupportRushDelivery() {
    when(media1.getWeight()).thenReturn(0.7);
    when(media2.getWeight()).thenReturn(0.2);

    when(orderMedia1.getMedia()).thenReturn(media1);
    when(orderMedia1.getQuantity()).thenReturn(2);
    when(orderMedia1.getIsOrderForRushDelivery()).thenReturn(true);

    when(orderMedia2.getMedia()).thenReturn(media2);
    when(orderMedia2.getQuantity()).thenReturn(1);
    when(orderMedia2.getIsOrderForRushDelivery()).thenReturn(false);

    List<OrderMedia> lstOrderMedia = Arrays.asList(orderMedia1, orderMedia2);

    String haiphongCity = ProvinceEnum.HAIPHONG.getStringValue();
    DeliveryInfo mockDeliveryInfo = mock(DeliveryInfo.class);
    when(mockDeliveryInfo.getCity()).thenReturn(haiphongCity);

    when(mockOrder.getDeliveryInfo()).thenReturn(mockDeliveryInfo);
    when(mockOrder.getOrderMediaList()).thenReturn(lstOrderMedia);
    when(mockOrder.getSubtotal()).thenReturn(120000.0);

    Double result = calculationService.calculateDeliveryFee(mockOrder);

    assertEquals(20000.0, result);
  }

  @Test
  void testCalculateRushDeliveryFee_allProductsSupportRushDelivery() {
    when(media1.getWeight()).thenReturn(0.7);
    when(media2.getWeight()).thenReturn(0.2);

    when(orderMedia1.getMedia()).thenReturn(media1);
    when(orderMedia1.getQuantity()).thenReturn(2);
    when(orderMedia1.getIsOrderForRushDelivery()).thenReturn(true);

    when(orderMedia2.getMedia()).thenReturn(media2);
    when(orderMedia2.getQuantity()).thenReturn(1);
    when(orderMedia2.getIsOrderForRushDelivery()).thenReturn(true);

    List<OrderMedia> lstOrderMedia = Arrays.asList(orderMedia1, orderMedia2);

    String haiphongCity = ProvinceEnum.HAIPHONG.getStringValue();
    DeliveryInfo mockDeliveryInfo = mock(DeliveryInfo.class);
    when(mockDeliveryInfo.getCity()).thenReturn(haiphongCity);

    when(mockOrder.getDeliveryInfo()).thenReturn(mockDeliveryInfo);
    when(mockOrder.getOrderMediaList()).thenReturn(lstOrderMedia);
    when(mockOrder.getSubtotal()).thenReturn(90000.0);

    Double result = calculationService.calculateDeliveryFee(mockOrder);

    assertEquals(62500.0, result);
  }
}

