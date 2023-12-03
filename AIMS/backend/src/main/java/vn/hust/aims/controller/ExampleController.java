package vn.hust.aims.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hust.aims.controller.dto.request.ExampleRequestGet;
import vn.hust.aims.controller.dto.request.ExampleRequestPost;
import vn.hust.aims.controller.dto.response.ExampleResponseGet;
import vn.hust.aims.controller.dto.response.ExampleResponsePost;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.ExampleService;
import vn.hust.aims.utils.PageableUtils;
import vn.hust.aims.utils.ResponseUtil;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/example")
public class ExampleController {

  private final ExampleService exampleService;

  @GetMapping("")
  public ResponseEntity<AimsCommonResponse<Object>> getExampleId(
      ExampleRequestGet request
  ) {
    return ResponseUtil.toSuccessCommonResponse(exampleService.getExample(request.toInput()));
  }

  @GetMapping("/pagination")
  public ResponseEntity<AimsCommonResponse<Object>> getExampleIdPagination(
      ExampleRequestGet request
  ) {
    Pageable pageable = PageableUtils.generate(request.getPage(), request.getSize(), "");
    return ResponseUtil.toSuccessCommonResponse(ExampleResponseGet.from(
        exampleService.getExamplePagePagination(request.toInput(), pageable))
    );
  }

  @PostMapping("")
  public ResponseEntity<AimsCommonResponse<Object>> addExample(
      @RequestBody ExampleRequestPost request
  ) {
    return ResponseUtil.toSuccessCommonResponse(
        ExampleResponsePost.from(exampleService.saveExample(request.toInput())));
  }
}
