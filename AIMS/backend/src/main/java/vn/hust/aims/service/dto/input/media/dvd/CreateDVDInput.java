package vn.hust.aims.service.dto.input.media.dvd;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.enumeration.DiscFormat;
import vn.hust.aims.service.dto.input.media.CreateMediaInput;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CreateDVDInput extends CreateMediaInput {

    private DiscFormat discFormat;

    private String director;

    private Double runTime;

    private String studio;

    private String language;

    private String subtitles;

    private String genres;

    private Instant releaseDate;

}
