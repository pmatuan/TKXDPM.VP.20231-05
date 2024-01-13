package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.hust.aims.controller.dto.request.media.DeleteMediaBulkRequest;
import vn.hust.aims.controller.dto.response.media.*;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.exception.AimsException;
import vn.hust.aims.exception.ErrorCodeList;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.dto.input.media.CreateMediaInput;
import vn.hust.aims.service.dto.input.media.GetAllMediaInput;
import vn.hust.aims.service.dto.input.media.GetMediaInput;
import vn.hust.aims.service.dto.input.media.UpdateMediaInput;
import vn.hust.aims.service.dto.output.media.*;
import vn.hust.aims.service.MediaService;
import vn.hust.aims.enumeration.MediaType;
import vn.hust.aims.utils.ResponseUtil;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/media")
public class MediaController {
    private final MediaService mediaService;

    @GetMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> getAllMedia(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "20") int size,
                                                                  @RequestParam(defaultValue = "media") String type,
                                                                  @RequestParam Map<String, Object> params) {
        params.remove("page");
        params.remove("size");
        params.remove("type");

        GetAllMediaOutput getAllMediaOutput = mediaService.getAllMedia(GetAllMediaInput.builder()
                .page(page)
                .size(size)
                .type(type)
                .filterMap(params)
                .build());

        return ResponseUtil.toSuccessCommonResponse(GetAllMediaResponse.from(getAllMediaOutput));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Media> getMedia(@PathVariable Long id) {
        GetMediaOutput getMediaOutput = mediaService.getMedia(GetMediaInput.builder().id(id).build());
        Media media = GetMediaResponse.from(getMediaOutput).getMedia();
        return new ResponseEntity<>(media, HttpStatus.OK);
    }

    @PostMapping("/image")
    public ResponseEntity<Object> testImage(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam Map<String, Object> body) {
        System.out.println(file);

        return ResponseEntity.ok(body);
    }


    @PostMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> createMedia(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam String mediaType, @RequestParam Map<String, Object> mediaInfo) {
        if (file != null) {
            String imageUrl = mediaService.createMediaImage(file);
            mediaInfo.put("imageUrl", imageUrl);
        }

        mediaInfo.remove("mediaType");

        CreateMediaOutput createMediaOutput = mediaService.createMedia(CreateMediaInput.builder()
                .mediaInfo(mediaInfo).mediaType(MediaType.from(mediaType)).build());

        return ResponseUtil.toSuccessCommonResponse(CreateMediaResponse.from(createMediaOutput));
    }


    @DeleteMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> deleteBulkMedia(@RequestBody DeleteMediaBulkRequest deleteMediaBulkRequest) {

        DeleteMediaBulkOutput deleteMediaBulkOutput;
        try {
            deleteMediaBulkOutput = mediaService.deleteBulkMedia(deleteMediaBulkRequest.toInput());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(AimsCommonResponse.badRequest(e.getMessage()));
        }

        DeleteMediaBulkResponse deleteMediaBulkResponse = DeleteMediaBulkResponse.from(deleteMediaBulkOutput);

        return ResponseUtil.toSuccessCommonResponse(deleteMediaBulkResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AimsCommonResponse<Object>> updateMedia(@PathVariable Long id,
                                                                  @RequestParam(value = "file", required = false) MultipartFile file,
                                                                  @RequestParam Map<String, Object> mediaInfo) {
        if (file != null) {
            String imageUrl = mediaService.createMediaImage(file);
            mediaInfo.put("imageUrl", imageUrl);
        }

        System.out.println(mediaInfo);

        Long authorId = Long.getLong((String) mediaInfo.get("authorId"));
        mediaInfo.remove("authorId");


        UpdateMediaOutput updateMediaOutput = mediaService.updateMedia(UpdateMediaInput.builder()
                    .MediaId(id)
                    .authorId(authorId)
                    .mediaInfo(mediaInfo)
                    .build()
        );

        return ResponseUtil.toSuccessCommonResponse(UpdateMediaResponse.from(updateMediaOutput));
    }

    @GetMapping("/images/{image}")
    public ResponseEntity<byte[]> getMediaImage(@PathVariable String image) {
        byte[] imageBytes = mediaService.getMediaImage(image);

        return ResponseEntity.ok().contentType(org.springframework.http.MediaType.IMAGE_JPEG).body(imageBytes);
    }
}
