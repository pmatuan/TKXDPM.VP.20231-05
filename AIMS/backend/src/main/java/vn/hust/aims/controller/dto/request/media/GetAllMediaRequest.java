package vn.hust.aims.controller.dto.request.media;

import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Data
@ParameterObject
public class GetAllMediaRequest {
    private Integer page;
    private Integer size;

    private Map<String, Object> params;
}
