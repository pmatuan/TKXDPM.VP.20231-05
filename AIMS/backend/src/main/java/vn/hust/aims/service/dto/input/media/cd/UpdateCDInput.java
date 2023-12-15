package vn.hust.aims.service.dto.input.media.cd;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.service.dto.input.media.UpdateMediaInput;

import javax.persistence.Column;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class UpdateCDInput extends UpdateMediaInput {

    private String artists;

    private String recordLabel;

    private String trackList;

    private String genres;

    private Instant releaseDate;

}
