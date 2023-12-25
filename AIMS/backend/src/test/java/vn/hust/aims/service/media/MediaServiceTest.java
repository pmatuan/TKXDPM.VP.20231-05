package vn.hust.aims.service.media;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.exception.QuantityNotEnoughException;

@ExtendWith(MockitoExtension.class)
class MediaServiceTest {

  @InjectMocks
  private MediaService mediaService;

  @Test
  void testValidateQuantityInStock_LessThanQuantityInStock() {
    Media media = new Media();
    media.setQuantityInStock(10);
    int requestedQuantity = 5;

    assertDoesNotThrow(() -> mediaService.validateQuantityInStock(media, requestedQuantity));
  }

  @Test
  void testValidateQuantityInStock_EqualToQuantityInStock() {
    Media media = new Media();
    media.setQuantityInStock(10);
    int requestedQuantity = 10;

    assertDoesNotThrow(() -> mediaService.validateQuantityInStock(media, requestedQuantity));
  }

  @Test
  void testValidateQuantityInStock_GreaterThanQuantityInStock() {
    Media media = new Media();
    media.setQuantityInStock(10);
    int requestedQuantity = 15;

    assertThrows(QuantityNotEnoughException.class, () -> mediaService.validateQuantityInStock(media, requestedQuantity));
  }

}