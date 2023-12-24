package vn.hust.aims.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.Example;
import vn.hust.aims.repository.ExampleRepository;
import vn.hust.aims.service.dto.input.ExampleGetInput;
import vn.hust.aims.service.dto.input.ExamplePostInput;
import vn.hust.aims.service.dto.output.ExampleGetOutput;
import vn.hust.aims.service.dto.output.ExamplePostOutput;

@Service
@RequiredArgsConstructor
public class ExampleService {

  private final ExampleRepository exampleRepository;

  public List<ExampleGetOutput> getExample(ExampleGetInput input) {
    List<Example> examples = exampleRepository.findAll();
    List<ExampleGetOutput> exampleGetOutputList = examples.stream()
        .map(ExampleGetOutput::fromExample)
        .collect(Collectors.toList());
    return exampleGetOutputList;
  }

  public Page<ExampleGetOutput> getExamplePagePagination(ExampleGetInput input, Pageable pageable) {
    input.normalize();
    Page<Example> examplePage = exampleRepository.getExamplePage(input.getDescription(),
        input.getCreatedFrom(), input.getCreatedTo(), input.getCreatedBy(), pageable);
    return examplePage.map(ExampleGetOutput::fromExample);
  }

  public ExamplePostOutput saveExample(ExamplePostInput input) {
    Example example = Example.builder()
        .description(input.getDescription())
        .createdAt(Instant.now())
        .createdBy(input.getCreatedBy())
        .build();
    exampleRepository.save(example);
    ExamplePostOutput output = ExamplePostOutput.builder()
        .message("Success")
        .build();
    return output;
  }
}
