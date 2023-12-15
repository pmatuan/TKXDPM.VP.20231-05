package vn.hust.aims.service.media;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.enumeration.MediaType;
import vn.hust.aims.repository.media.MediaRepository;
import vn.hust.aims.service.dto.input.media.*;
import vn.hust.aims.service.dto.output.media.*;
import vn.hust.aims.service.media.factory.MediaFactory;
import vn.hust.aims.service.media.factory.MediaFactoryBuilder;


@Service
@AllArgsConstructor
public class MediaCRUDService {
    private final MediaRepository mediaRepository;

    public CreateMediaOutput create(CreateMediaInput createMediaInput) {
        MediaFactory mediaFactory = MediaFactoryBuilder.get(createMediaInput.getMediaType());
        Media media = mediaFactory.build(createMediaInput.getJsonPayload());

        mediaRepository.save(media);
        return CreateMediaOutput.from("Create success");
    }
    public GetMediaOutput get(GetMediaInput getMediaInput) {
        Media media = mediaRepository.findById(getMediaInput.getId()).orElseThrow();

        return GetMediaOutput.builder().media(media).build();
    }
    public GetAllMediaOutput getAll(GetAllMediaInput getAllMediaInput) {
        PageRequest pageRequest = PageRequest.of(getAllMediaInput.getPage(), getAllMediaInput.getSize());

        return GetAllMediaOutput.builder().mediaPage(mediaRepository.findAll(pageRequest)).build();
    }

    public DeleteMediaBulkOutput deleteBulk(DeleteMediaBulkInput deleteMediaBulkInput) {
        if (deleteMediaBulkInput.getIds().isEmpty()) {
            mediaRepository.deleteAll();
            return DeleteMediaBulkOutput.from("Delete all success");
        }

        mediaRepository.deleteAllById(deleteMediaBulkInput.getIds());
        return DeleteMediaBulkOutput.from("Delete bulk success");
    }

    public UpdateMediaOutput updateMedia(UpdateMediaInput updateMediaInput) {
        Long id = updateMediaInput.getId();

        String type = mediaRepository.findById(id).orElseThrow().getCategory();
        MediaFactory mediaFactory = MediaFactoryBuilder.get(MediaType.from(type));

        Media media = mediaFactory.build(updateMediaInput.getJsonPayload());

        media.setId(id);

        mediaRepository.save(media);

        return UpdateMediaOutput.from("Update success");
    }

}
