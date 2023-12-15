package vn.hust.aims.controller.dto.response.media.dvd;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.controller.dto.response.media.GetMediaResponse;
import vn.hust.aims.entity.media.DVD;
import vn.hust.aims.enumeration.DiscFormat;
import vn.hust.aims.service.dto.output.media.dvd.GetDVDOutput;

import javax.persistence.Column;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDVDResponse extends GetMediaResponse {

    private String discFormat;

    private String director;

    private Double runTime;

    private String studio;

    private String language;

    private String subtitles;

    private String genres;

    private Instant releaseDate;
    
    public static GetDVDResponse from(GetDVDOutput getDVDOutput) {
        DVD dvd = getDVDOutput.getDvd();
        
        return GetDVDResponse
                .builder()
                .title(dvd.getTitle())
                .category(dvd.getCategory())
                .value(dvd.getValue())
                .price(dvd.getPrice())
                .quantityInStock(dvd.getQuantityInStock())
                .isAbleToRushDelivery(dvd.getIsAbleToRushDelivery())
                .weight(dvd.getWeight())
                .imageUrl(dvd.getImageUrl())
                .barcodeUrl(dvd.getBarcodeUrl())
                .description(dvd.getDescription())
                .importDate(dvd.getImportDate())

                .discFormat(dvd.getDiscFormat().toString())
                .director(dvd.getDirector())
                .runTime(dvd.getRunTime())
                .studio(dvd.getStudio())
                .language(dvd.getLanguage())
                .subtitles(dvd.getSubtitles())
                .genres(dvd.getGenres())
                .releaseDate(dvd.getReleaseDate())
                .build();
    }
    
}
