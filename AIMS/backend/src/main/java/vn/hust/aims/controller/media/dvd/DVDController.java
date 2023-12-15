package vn.hust.aims.controller.media.dvd;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.hust.aims.controller.dto.request.media.cd.UpdateCDRequest;
import vn.hust.aims.controller.dto.request.media.dvd.CreateDVDRequest;
import vn.hust.aims.controller.dto.request.media.dvd.UpdateDVDRequest;
import vn.hust.aims.controller.dto.response.media.cd.UpdateCDResponse;
import vn.hust.aims.controller.dto.response.media.dvd.CreateDVDResponse;
import vn.hust.aims.controller.dto.response.media.dvd.GetDVDResponse;
import vn.hust.aims.controller.dto.response.media.dvd.UpdateDVDResponse;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.dto.input.media.dvd.GetDVDInput;
import vn.hust.aims.service.dto.output.media.cd.UpdateCDOutput;
import vn.hust.aims.service.dto.output.media.dvd.CreateDVDOutput;
import vn.hust.aims.service.dto.output.media.dvd.GetDVDOutput;
import vn.hust.aims.service.dto.output.media.dvd.UpdateDVDOutput;
import vn.hust.aims.service.media.dvd.DVDService;
import vn.hust.aims.utils.ResponseUtil;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/dvd")
public class DVDController {
    private final DVDService dvdService;

    @PostMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> createDVD(@RequestBody CreateDVDRequest createDVDRequest) {
        CreateDVDOutput output = dvdService.createDVD(createDVDRequest.toInput());

        return ResponseUtil.toSuccessCommonResponse(
                CreateDVDResponse.from(output)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AimsCommonResponse<Object>> getDVD(@PathVariable Long id) {
        GetDVDOutput getDVDOutput = dvdService.getDVD(GetDVDInput.builder().id(id).build());

        return ResponseUtil.toSuccessCommonResponse(
                GetDVDResponse.from(getDVDOutput)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AimsCommonResponse<Object>> updateDVD(@PathVariable Long id, @RequestBody UpdateDVDRequest updateDVDRequest) {
        UpdateDVDOutput updateDVDOutput = dvdService.updateDVD(updateDVDRequest.toInput(id));

        return ResponseUtil.toSuccessCommonResponse(
                UpdateDVDResponse.from(updateDVDOutput)
        );
    }

}
