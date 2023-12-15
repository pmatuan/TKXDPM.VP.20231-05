package vn.hust.aims.controller.media.cd;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.hust.aims.controller.dto.request.media.cd.UpdateCDRequest;
import vn.hust.aims.controller.dto.response.media.cd.GetCDResponse;
import vn.hust.aims.controller.dto.response.media.cd.UpdateCDResponse;
import vn.hust.aims.service.dto.input.media.cd.GetCDInput;
import vn.hust.aims.service.dto.output.media.cd.GetCDOutput;
import vn.hust.aims.service.dto.output.media.cd.UpdateCDOutput;
import vn.hust.aims.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import vn.hust.aims.controller.dto.request.media.cd.CreateCDRequest;
import vn.hust.aims.controller.dto.response.media.cd.CreateCDResponse;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.dto.output.media.cd.CreateCDOutput;
import vn.hust.aims.service.media.cd.CDService;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/cd")
public class CDController {
    private final CDService cdService;

    @PostMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> createCd(@RequestBody CreateCDRequest createCDRequest) {
        CreateCDOutput output = cdService.createCD(createCDRequest.toInput());


        return ResponseUtil.toSuccessCommonResponse(
                CreateCDResponse.from(output)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AimsCommonResponse<Object>> getCD(@PathVariable Long id) {
        GetCDOutput getCDOutput = cdService.getCD(GetCDInput.builder().id(id).build());

        return ResponseUtil.toSuccessCommonResponse(
                GetCDResponse.from(getCDOutput)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AimsCommonResponse<Object>> updateCD(@PathVariable Long id, @RequestBody UpdateCDRequest updateCDRequest) {
        UpdateCDOutput updateCDOutput = cdService.updateCD(updateCDRequest.toInput(id));

        return ResponseUtil.toSuccessCommonResponse(
                UpdateCDResponse.from(updateCDOutput)
        );
    }
}
