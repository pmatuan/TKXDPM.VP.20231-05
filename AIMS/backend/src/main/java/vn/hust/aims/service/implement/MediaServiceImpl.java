package vn.hust.aims.service.implement;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.hust.aims.entity.media.Changelog;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.entity.media.factory.MediaFactoryBuilder;
import vn.hust.aims.entity.media.factory.MediaFactoryInterface;
import vn.hust.aims.enumeration.MediaType;
import vn.hust.aims.exception.GetImageException;
import vn.hust.aims.exception.MediaNotFoundException;
import vn.hust.aims.exception.PriceChangeLimitExceededException;
import vn.hust.aims.exception.QuantityNotEnoughException;
import vn.hust.aims.exception.UpdateDeleteLimitExceededException;
import vn.hust.aims.repository.media.ChangelogRepository;
import vn.hust.aims.repository.media.MediaRepository;
import vn.hust.aims.service.MediaService;
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
import vn.hust.aims.utils.JsonMapper;

@Service
@AllArgsConstructor
public class MediaServiceImpl implements MediaService {
  private final MediaRepository mediaRepository;
  private final ChangelogRepository changelogRepository;
  private final ResourceLoader resourceLoader;

  public CreateMediaOutput createMedia(CreateMediaInput createMediaInput) {
    MediaFactoryInterface mediaFactoryInterface = MediaFactoryBuilder.get(createMediaInput.getMediaType());

    Media media = mediaFactoryInterface.build(JsonMapper.writeValueAsString(createMediaInput.getMediaInfo()));

    mediaRepository.save(media);
    return CreateMediaOutput.from("Create success");
  }

  public GetMediaOutput getMedia(GetMediaInput getMediaInput) {
    Media media = mediaRepository.findById(getMediaInput.getId()).orElseThrow(
        MediaNotFoundException::new);

    return GetMediaOutput.builder().media(media).build();
  }

  public GetAllMediaOutput getAllMedia(GetAllMediaInput getAllMediaInput) {

    Map<String, Object> filterMap = getAllMediaInput.getFilterMap();
    int size = getAllMediaInput.getSize();
    int page = getAllMediaInput.getPage();
    String type = getAllMediaInput.getType();

    Media media = MediaFactoryBuilder.get(MediaType.from(type))
        .build(JsonMapper.writeValueAsString(filterMap));
//
//    System.out.println(media);

    PageRequest pageRequest = PageRequest.of(page, size);

    Page<Media> mediaPage;

    if (media.getTitle() == null) {
      mediaPage = mediaRepository.findAll(Example.of(media), pageRequest);
    } else {
      mediaPage = mediaRepository.findAllByTitleContaining(media.getTitle(), pageRequest);
    }

    return GetAllMediaOutput.builder().mediaPage(mediaPage).build();
  }

  public DeleteMediaBulkOutput deleteBulkMedia(DeleteMediaBulkInput deleteMediaBulkInput) {
    if (deleteMediaBulkInput.getIds().isEmpty()) {
      mediaRepository.deleteAll();
      return DeleteMediaBulkOutput.from("Delete all success");
    }

    Instant now = Instant.now();

    List<Changelog> listChangelog = changelogRepository.findAllByAuthorIdAndTimestampAfter(deleteMediaBulkInput.getAuthorId(), now.minus(1, ChronoUnit.DAYS));

    if (deleteMediaBulkInput.getIds().size() + listChangelog.size() > 30) {
      throw new UpdateDeleteLimitExceededException();
    }

    for (Long id: deleteMediaBulkInput.getIds()) {
      Changelog changelog = Changelog.builder()
          .timestamp(now)
          .authorId(deleteMediaBulkInput.getAuthorId())
          .changedMediaId(id)
          .isPriceChange(0)
          .build();

      changelogRepository.save(changelog);
    }

    mediaRepository.deleteAllById(deleteMediaBulkInput.getIds());

    return DeleteMediaBulkOutput.from("Delete bulk success");
  }

  public UpdateMediaOutput updateMedia(UpdateMediaInput updateMediaInput) {
    Long id = updateMediaInput.getMediaId();

    Media mediaEntity = mediaRepository.getReferenceById(id);

    MediaFactoryInterface mediaFactoryInterface = MediaFactoryBuilder.get(MediaType.from(mediaEntity.getCategory()));

    updateMediaInput.getMediaInfo().put("createdAt", mediaEntity.getCreatedAt());

    Media toUpdate = mediaFactoryInterface.build(JsonMapper.writeValueAsString(updateMediaInput.getMediaInfo()));
    toUpdate.setId(id);

    Instant now = Instant.now();

    Changelog changelog = Changelog.builder()
        .changedMediaId(id)
        .timestamp(now)
        .build();

    List<Changelog> listChangelog = changelogRepository.findAllByAuthorIdAndTimestampAfter(updateMediaInput.getAuthorId(), now.minus(1, ChronoUnit.DAYS));

    if (listChangelog.size() > 30) {
      throw new UpdateDeleteLimitExceededException();
    } else {
      if (!Objects.equals(mediaEntity.getPrice(), toUpdate.getPrice())) {
        changelog.setIsPriceChange(1);

        List<Changelog> listPriceChange = changelogRepository.findAllByChangedMediaIdAndTimestampAfterAndIsPriceChange(id, now.minus(1, ChronoUnit.DAYS), 1);
        if (listPriceChange.size() > 1) {
          throw new PriceChangeLimitExceededException();
        }
      } else {
        changelog.setIsPriceChange(0);
      }
    }

    changelogRepository.save(changelog);

    System.out.println(toUpdate);

    mediaRepository.save(toUpdate);

    return UpdateMediaOutput.from("Update success");
  }

  public String createMediaImage(MultipartFile file) {
    try {
      String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

      // Get the resource directory
      Resource resourceDir = resourceLoader.getResource("classpath:images/");

      // Create the path for the new file
      Path filePath = Paths.get(resourceDir.getURI()).resolve(filename);

      // Write the file content to the specified path
      Files.write(filePath, file.getBytes());

      return filename;
    } catch (IOException e) {
      // Handle exceptions accordingly, e.g., log or throw a custom exception
      throw new RuntimeException("Failed to upload image", e);
    }
  }

  public byte[] getMediaImage(String imageName) {
    try {
      Resource resource = resourceLoader.getResource("classpath:images/" + imageName);
      System.out.println(resource.getURI());
      try (InputStream inputStream = resource.getInputStream()) {
        return inputStream.readAllBytes();
      }
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

  public void updateQuantityInStock(Media media, Integer newQuantity) {
    media.setQuantityInStock(newQuantity);
    mediaRepository.save(media);
  }
}
