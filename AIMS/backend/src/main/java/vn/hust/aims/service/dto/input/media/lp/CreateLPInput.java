package vn.hust.aims.service.dto.input.media.lp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.input.media.CreateMediaInput;

import javax.persistence.Column;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CreateLPInput extends CreateMediaInput {
    private String artists;

    private String recordLabel;

    private String trackList;

    private String genres;

    private Instant releaseDate;
}
