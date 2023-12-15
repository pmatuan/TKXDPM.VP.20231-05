package vn.hust.aims.service.dto.output.media.cd;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.media.CD;

@Data
@SuperBuilder
public class GetCDOutput {
    private CD cd;

    public static GetCDOutput from(CD cd) {
        return GetCDOutput.builder().cd(cd).build();
    }
}
