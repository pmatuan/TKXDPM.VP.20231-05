package vn.hust.aims.service.dto.output.media.dvd;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.media.DVD;
import vn.hust.aims.service.dto.output.media.book.GetBookOutput;

@Data
@SuperBuilder
public class GetDVDOutput {
    private DVD dvd;

    public static GetDVDOutput from(DVD dvd) {
        return GetDVDOutput.builder().dvd(dvd).build();
    }
}
