package vn.hust.aims.service.dto.output.media.lp;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.media.DVD;
import vn.hust.aims.entity.media.LP;

@Data
@SuperBuilder
public class GetLPOutput {
    private LP lp;

    public static GetLPOutput from(LP lp) {

        return GetLPOutput
                .builder()
                .lp(lp)
                .build();
    }
}
