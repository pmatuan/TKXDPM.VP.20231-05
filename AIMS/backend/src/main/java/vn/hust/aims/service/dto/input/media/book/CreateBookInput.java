package vn.hust.aims.service.dto.input.media.book;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.enumeration.CoverType;
import vn.hust.aims.service.dto.input.media.CreateMediaInput;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CreateBookInput extends CreateMediaInput {
    private String authors;

    private CoverType coverType;

    private String publisher;

    private Instant publicationDate;

    private Integer pages;

    private String language;

    private String type;
}