package vn.hust.aims.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.exception.MediaNotFoundException;
import vn.hust.aims.exception.QuantityNotEnoughException;
import vn.hust.aims.repository.media.MediaRepository;

@Service
@AllArgsConstructor
public class MediaService {

  private final MediaRepository mediaRepository;

  // data-coupling
  // Chỉ dùng đủ dữ liệu để tìm media - mediaId
  public Media getMediaById(Long mediaId) {
    return mediaRepository.findById(mediaId)
        .orElseThrow(() -> new MediaNotFoundException());
  }

  // data-coupling
  // Chỉ dùng đủ dữ liệu để tìm cart media - chỉ truyền vào media và số quantity cần có
  public void validateQuantityInStock(Media media, Integer requestedQuantity) {
    if (media.getQuantityInStock() < requestedQuantity) {
      throw new QuantityNotEnoughException();
    }
  }
}
