package vn.hust.aims.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.hust.aims.controller.dto.request.media.DeleteMediaBulkRequest;
import vn.hust.aims.controller.dto.response.media.*;
import vn.hust.aims.service.media.factory.MediaType;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.dto.input.media.CreateMediaInput;
import vn.hust.aims.service.dto.input.media.GetAllMediaInput;
import vn.hust.aims.service.dto.input.media.GetMediaInput;
import vn.hust.aims.service.dto.input.media.UpdateMediaInput;
import vn.hust.aims.service.dto.output.media.*;
import vn.hust.aims.service.media.MediaService;
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
    public ResponseEntity<AimsCommonResponse<Object>> getMedia(@PathVariable Long id) {
        GetMediaOutput getMediaOutput = mediaService.getMedia(GetMediaInput.builder().id(id).build());

        return ResponseUtil.toSuccessCommonResponse(GetMediaResponse.from(getMediaOutput));
    }

    @PostMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> createMedia(@RequestParam String type,
                                                             @RequestBody String jsonPayload) {

        System.out.println(MediaType.from(type));

        CreateMediaOutput createMediaOutput = mediaService.createMedia(CreateMediaInput.builder()
                .jsonPayload(jsonPayload).mediaType(MediaType.from(type)).build());

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
    public ResponseEntity<AimsCommonResponse<Object>> updateMedia(@PathVariable Long id, @RequestBody String jsonPayload) {
        UpdateMediaOutput updateMediaOutput = mediaService.updateMedia(UpdateMediaInput.builder().id(id).jsonPayload(jsonPayload).build());

        return ResponseUtil.toSuccessCommonResponse(UpdateMediaResponse.from(updateMediaOutput));
    }
}
