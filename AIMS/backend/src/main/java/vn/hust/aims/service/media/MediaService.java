package vn.hust.aims.service.media;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.hust.aims.entity.media.Changelog;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.exception.*;
import vn.hust.aims.repository.media.ChangelogRepository;
import vn.hust.aims.repository.media.MediaRepository;
import vn.hust.aims.service.dto.input.media.*;
import vn.hust.aims.service.dto.output.media.*;
import vn.hust.aims.service.media.factory.MediaFactoryBuilder;
import vn.hust.aims.service.media.factory.MediaFactoryInterface;
import vn.hust.aims.service.media.factory.MediaType;
import vn.hust.aims.utils.JsonMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


@Service
@AllArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    private final ChangelogRepository changelogRepository;
    private final String uploadPath = System.getProperty("user.dir") + "/src/main/java/vn/hust/aims/service/media/images";

    public CreateMediaOutput createMedia(CreateMediaInput createMediaInput) {
        MediaFactoryInterface mediaFactoryInterface = MediaFactoryBuilder.get(createMediaInput.getMediaType());

        Media media = mediaFactoryInterface.build(JsonMapper.writeValueAsString(createMediaInput.getMediaInfo()));

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

        Instant now = Instant.now();

        List<Changelog> listChangelog = changelogRepository.findAllByTimestampAfter(now.minus(24, ChronoUnit.HOURS));

        if (deleteMediaBulkInput.getIds().size() + listChangelog.size() > 30) {
            throw new UpdateDeleteLimitExceededException();
        }

        for (Long id: deleteMediaBulkInput.getIds()) {
            Changelog changelog = Changelog.builder()
                    .timestamp(now)
                    .changedMediaId(id)
                    .isPriceChange(0)
                    .build();
            
            changelogRepository.save(changelog);
        }

        mediaRepository.deleteAllById(deleteMediaBulkInput.getIds());

        return DeleteMediaBulkOutput.from("Delete bulk success");
    }

    public UpdateMediaOutput updateMedia(UpdateMediaInput updateMediaInput) {
        Long id = updateMediaInput.getId();

        Media mediaEntity = mediaRepository.getReferenceById(id);

        MediaFactoryInterface mediaFactoryInterface = MediaFactoryBuilder.get(MediaType.from(mediaEntity.getCategory()));

        Media toUpdate = mediaFactoryInterface.build(JsonMapper.writeValueAsString(updateMediaInput.getMediaInfo()));
        toUpdate.setId(id);

        Instant now = Instant.now();

        Changelog changelog = Changelog.builder()
                .changedMediaId(id)
                .timestamp(now)
                .build();

        List<Changelog> listChangelog = changelogRepository.findAllByTimestampAfter(now.minus(10, ChronoUnit.SECONDS));

        if (listChangelog.size() > 29) {
            throw new UpdateDeleteLimitExceededException();
        } else {
            if (!Objects.equals(mediaEntity.getPrice(), toUpdate.getPrice())) {
                changelog.setIsPriceChange(1);

                List<Changelog> listPriceChange = changelogRepository.findAllByChangedMediaIdAndTimestampAfterAndIsPriceChange(id, now.minus(2, ChronoUnit.MINUTES), 1);
                if (listPriceChange.size() > 1) {
                    throw new PriceChangeLimitExceededException();
                }
            } else {
                changelog.setIsPriceChange(0);
            }
        }

        changelogRepository.save(changelog);

        mediaRepository.save(toUpdate);

        return UpdateMediaOutput.from("Update success");
    }

    public String createMediaImage(MultipartFile file) {
        try {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path filePath = Paths.get(uploadPath, filename);

            Files.write(filePath, file.getBytes());

            return filename;
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException("Failed to upload image");
        }
    }

    public byte[] getMediaImage(String imageName) {
        try {
            Path filePath = Paths.get(uploadPath, imageName);

            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new GetImageException();
        }
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
