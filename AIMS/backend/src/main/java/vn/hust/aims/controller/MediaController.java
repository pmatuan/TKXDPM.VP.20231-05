package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.hust.aims.controller.dto.request.media.DeleteMediaBulkRequest;
import vn.hust.aims.controller.dto.response.media.*;
import vn.hust.aims.enumeration.MediaType;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.dto.input.media.CreateMediaInput;
import vn.hust.aims.service.dto.input.media.GetAllMediaInput;
import vn.hust.aims.service.dto.input.media.GetMediaInput;
import vn.hust.aims.service.dto.input.media.UpdateMediaInput;
import vn.hust.aims.service.dto.output.media.*;
import vn.hust.aims.service.media.MediaCRUDService;
import vn.hust.aims.utils.ResponseUtil;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/media")
public class MediaController {
    private final MediaCRUDService mediaCRUDService;

    @GetMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        GetAllMediaOutput getAllMediaOutput = mediaCRUDService.getAll(GetAllMediaInput.builder()
                .page(page)
                .size(size)
                .build());

        return ResponseUtil.toSuccessCommonResponse(GetAllMediaResponse.from(getAllMediaOutput));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AimsCommonResponse<Object>> get(@PathVariable Long id) {
        GetMediaOutput getMediaOutput = mediaCRUDService.get(GetMediaInput.builder().id(id).build());

        return ResponseUtil.toSuccessCommonResponse(GetMediaResponse.from(getMediaOutput));
    }

    @PostMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> create(@RequestParam(defaultValue = "book", required = true) String type,
                                                             @RequestBody String jsonPayload) {

        CreateMediaOutput createMediaOutput = mediaCRUDService.create(CreateMediaInput.builder()
                .jsonPayload(jsonPayload).mediaType(MediaType.from(type)).build());

        return ResponseUtil.toSuccessCommonResponse(CreateMediaResponse.from(createMediaOutput));
    }

    @DeleteMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> deleteBulk(@RequestBody DeleteMediaBulkRequest deleteMediaBulkRequest) {
        DeleteMediaBulkOutput deleteMediaBulkOutput = mediaCRUDService.deleteBulk(deleteMediaBulkRequest.toInput());

        DeleteMediaBulkResponse deleteMediaBulkResponse = DeleteMediaBulkResponse.from(deleteMediaBulkOutput);

        return ResponseUtil.toSuccessCommonResponse(deleteMediaBulkResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AimsCommonResponse<Object>> updateMedia(@PathVariable Long id, @RequestBody String jsonPayload) {
        UpdateMediaOutput updateMediaOutput = mediaCRUDService.updateMedia(UpdateMediaInput.builder().id(id).jsonPayload(jsonPayload).build());

        return ResponseUtil.toSuccessCommonResponse(UpdateMediaResponse.from(updateMediaOutput));
    }
}
