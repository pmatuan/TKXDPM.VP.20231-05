package vn.hust.aims.controller.media.media;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.hust.aims.controller.dto.request.media.DeleteMediaBulkRequest;
import vn.hust.aims.controller.dto.response.media.DeleteMediaBulkResponse;
import vn.hust.aims.controller.dto.response.media.GetAllMediaResponse;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.dto.output.media.DeleteMediaBulkOutput;
import vn.hust.aims.service.dto.output.media.GetAllMediaOutput;
import vn.hust.aims.service.media.MediaService;
import vn.hust.aims.utils.ResponseUtil;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/media")
public class MediaController {
    private final MediaService mediaService;

    @GetMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> getAll() {
        GetAllMediaOutput getAllMediaOutput = mediaService.getAll();

        return ResponseUtil.toSuccessCommonResponse(GetAllMediaResponse.from(getAllMediaOutput));
    }

    @DeleteMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> deleteBulk(@RequestBody DeleteMediaBulkRequest deleteMediaBulkRequest) {
        DeleteMediaBulkOutput deleteMediaBulkOutput = mediaService.deleteBulk(deleteMediaBulkRequest.toInput());

        DeleteMediaBulkResponse deleteMediaBulkResponse = DeleteMediaBulkResponse.from(deleteMediaBulkOutput);

        return ResponseUtil.toSuccessCommonResponse(deleteMediaBulkResponse);
    }
}
