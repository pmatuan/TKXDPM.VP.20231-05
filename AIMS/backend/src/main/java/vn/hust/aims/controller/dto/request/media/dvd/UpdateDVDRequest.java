package vn.hust.aims.controller.dto.request.media.dvd;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hust.aims.controller.dto.request.media.UpdateMediaRequest;
import vn.hust.aims.enumeration.DiscFormat;
import vn.hust.aims.service.dto.input.media.dvd.UpdateDVDInput;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateDVDRequest extends UpdateMediaRequest {
    private String discFormat;

    private String director;

    private Double runTime;

    private String studio;

    private String language;

    private String subtitles;

    private String genres;

    private Instant releaseDate;

    public UpdateDVDInput toInput(Long id) {
        return UpdateDVDInput.builder()
                .id(id)

                .title(this.title)
                .category(this.category)
                .value(this.value)
                .price(this.price)
                .quantityInStock(this.quantityInStock)
                .isAbleToRushDelivery(this.isAbleToRushDelivery)
                .weight(this.weight)
                .imageUrl(this.imageUrl)
                .barcodeUrl(this.barcodeUrl)
                .description(this.description)
                .importDate(this.importDate)

                .discFormat(DiscFormat.from(this.discFormat))
                .director(this.director)
                .runTime(this.runTime)
                .studio(this.studio)
                .language(this.language)
                .subtitles(this.subtitles)
                .genres(this.genres)
                .releaseDate(this.releaseDate)
                .build();


    }

}
