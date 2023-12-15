package vn.hust.aims.controller.media.lp;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.hust.aims.controller.dto.request.media.lp.CreateLPRequest;
import vn.hust.aims.controller.dto.request.media.lp.UpdateLPRequest;
import vn.hust.aims.controller.dto.response.media.lp.CreateLPResponse;
import vn.hust.aims.controller.dto.response.media.lp.GetLPResponse;
import vn.hust.aims.controller.dto.response.media.lp.UpdateLPResponse;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.dto.input.media.lp.GetLPInput;
import vn.hust.aims.service.dto.output.media.lp.CreateLPOutput;
import vn.hust.aims.service.dto.output.media.lp.GetLPOutput;
import vn.hust.aims.service.dto.output.media.lp.UpdateLPOutput;
import vn.hust.aims.service.media.lp.LPService;
import vn.hust.aims.utils.ResponseUtil;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/lp")
public class LPController {
    private final LPService lpService;

    @PostMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> createLP(@RequestBody CreateLPRequest createLPRequest) {
        CreateLPOutput output = lpService.createLP(createLPRequest.toInput());

        return ResponseUtil.toSuccessCommonResponse(
                CreateLPResponse.from(output)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<AimsCommonResponse<Object>> getBook(@PathVariable Long id) {

        GetLPOutput getLPOutput = lpService.getLP(GetLPInput.builder().id(id).build());

        return ResponseUtil.toSuccessCommonResponse(
                GetLPResponse.from(getLPOutput)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AimsCommonResponse<Object>> updateLP(@PathVariable Long id, @RequestBody UpdateLPRequest updateLPRequest) {
        UpdateLPOutput updateLPOutput = lpService.updateLP(updateLPRequest.toInput(id));

        return ResponseUtil.toSuccessCommonResponse(
                UpdateLPResponse.from(updateLPOutput)
        );
    }
}
