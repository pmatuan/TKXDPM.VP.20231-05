package vn.hust.aims.service;

import org.springframework.web.multipart.MultipartFile;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.service.dto.input.media.CreateMediaInput;
import vn.hust.aims.service.dto.input.media.DeleteMediaBulkInput;
import vn.hust.aims.service.dto.input.media.GetAllMediaInput;
import vn.hust.aims.service.dto.input.media.GetMediaInput;
import vn.hust.aims.service.dto.input.media.UpdateMediaInput;
import vn.hust.aims.service.dto.output.media.CreateMediaOutput;
import vn.hust.aims.service.dto.output.media.DeleteMediaBulkOutput;
import vn.hust.aims.service.dto.output.media.GetAllMediaOutput;
import vn.hust.aims.service.dto.output.media.GetMediaOutput;
import vn.hust.aims.service.dto.output.media.UpdateMediaOutput;

public interface MediaService {
    CreateMediaOutput createMedia(CreateMediaInput createMediaInput);
    GetMediaOutput getMedia(GetMediaInput getMediaInput);
    GetAllMediaOutput getAllMedia(GetAllMediaInput getAllMediaInput);
    DeleteMediaBulkOutput deleteBulkMedia(DeleteMediaBulkInput deleteMediaBulkInput);
    UpdateMediaOutput updateMedia(UpdateMediaInput updateMediaInput);
    String createMediaImage(MultipartFile file);
    byte[] getMediaImage(String imageName);
    Media getMediaById(Long mediaId);
    void validateQuantityInStock(Media media, Integer requestedQuantity);
    void updateQuantityInStock(Media media, Integer newQuantity);

}
