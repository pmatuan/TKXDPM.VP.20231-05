package vn.hust.aims.service.dto.output.media.cd;

import lombok.Data;
import lombok.experimental.SuperBuilder;



@Data
@SuperBuilder
public class CreateCDOutput {
    private String message;

    public static CreateCDOutput from(String message) { return CreateCDOutput.builder().message(message).build();}
}
