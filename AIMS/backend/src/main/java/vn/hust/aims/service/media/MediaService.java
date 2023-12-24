package vn.hust.aims.service.media;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.exception.MediaNotFoundException;
import vn.hust.aims.exception.QuantityNotEnoughException;
import vn.hust.aims.service.media.factory.MediaType;
import vn.hust.aims.repository.media.MediaRepository;
import vn.hust.aims.service.dto.input.media.*;
import vn.hust.aims.service.dto.output.media.*;
import vn.hust.aims.service.media.factory.MediaFactoryInterface;
import vn.hust.aims.service.media.factory.MediaFactoryBuilder;
import vn.hust.aims.utils.JsonMapper;

import java.util.Map;


@Service
@AllArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;

    public CreateMediaOutput createMedia(CreateMediaInput createMediaInput) {
        MediaFactoryInterface mediaFactoryInterface = MediaFactoryBuilder.get(createMediaInput.getMediaType());

        Media media = mediaFactoryInterface.build(createMediaInput.getJsonPayload());

        mediaRepository.save(media);
        return CreateMediaOutput.from("Create success");
    }
    public GetMediaOutput getMedia(GetMediaInput getMediaInput) {
        Media media = mediaRepository.findById(getMediaInput.getId()).orElseThrow(MediaNotFoundException::new);

        return GetMediaOutput.builder().media(media).build();
    }
    public GetAllMediaOutput getAllMedia(GetAllMediaInput getAllMediaInput) {

        Map<String, Object> filterMap = getAllMediaInput.getFilterMap();
        int size = getAllMediaInput.getSize();
        int page = getAllMediaInput.getPage();
        String type = getAllMediaInput.getType();

        Media media = MediaFactoryBuilder.get(MediaType.from(type))
                                        .build(JsonMapper.writeValueAsString(filterMap));

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Media> mediaPage = mediaRepository.findAll(Example.of(media), pageRequest);

        return GetAllMediaOutput.builder().mediaPage(mediaPage).build();
    }

    public DeleteMediaBulkOutput deleteBulkMedia(DeleteMediaBulkInput deleteMediaBulkInput) {
        if (deleteMediaBulkInput.getIds().isEmpty()) {
            mediaRepository.deleteAll();
            return DeleteMediaBulkOutput.from("Delete all success");
        }

        mediaRepository.deleteAllById(deleteMediaBulkInput.getIds());

        return DeleteMediaBulkOutput.from("Delete bulk success");
    }

    public UpdateMediaOutput updateMedia(UpdateMediaInput updateMediaInput) {
        Long id = updateMediaInput.getId();

        String type = mediaRepository.findById(id).orElseThrow(MediaNotFoundException::new).getCategory();
        MediaFactoryInterface mediaFactoryInterface = MediaFactoryBuilder.get(MediaType.from(type));

        Media media = mediaFactoryInterface.build(updateMediaInput.getJsonPayload());

        media.setId(id);

        mediaRepository.save(media);

        return UpdateMediaOutput.from("Update success");
    }

    // data-coupling
    // Chỉ dùng đủ dữ liệu để tìm media - mediaId
    public Media getMediaById(Long mediaId) {
        return mediaRepository.findById(mediaId)
                .orElseThrow(MediaNotFoundException::new);
    }

    // data-coupling
    // Chỉ dùng đủ dữ liệu để tìm cart media - chỉ truyền vào media và số quantity cần có
    public void validateQuantityInStock(Media media, Integer requestedQuantity) {
        if (media.getQuantityInStock() < requestedQuantity) {
            throw new QuantityNotEnoughException();
        }
    }

}
