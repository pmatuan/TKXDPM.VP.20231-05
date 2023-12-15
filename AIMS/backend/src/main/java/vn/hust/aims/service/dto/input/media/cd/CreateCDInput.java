package vn.hust.aims.service.dto.input.media.cd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.input.media.CreateMediaInput;

import javax.persistence.Column;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CreateCDInput extends CreateMediaInput {

    private String artists;

    private String recordLabel;

    private String trackList;

    private String genres;

    private Instant releaseDate;


}
