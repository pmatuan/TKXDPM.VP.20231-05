package vn.hust.aims.controller.dto.response.media.cd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.controller.dto.response.media.GetMediaResponse;
import vn.hust.aims.entity.media.CD;
import vn.hust.aims.service.dto.output.media.cd.GetCDOutput;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCDResponse extends GetMediaResponse {
    private String artists;

    private String recordLabel;

    private String trackList;

    private String genres;

    private Instant releaseDate;

    public static GetCDResponse from(GetCDOutput getCDOutput) {
        CD cd = getCDOutput.getCd();

        return GetCDResponse
                .builder()
                .title(cd.getTitle())
                .category(cd.getCategory())
                .value(cd.getValue())
                .price(cd.getPrice())
                .quantityInStock(cd.getQuantityInStock())
                .isAbleToRushDelivery(cd.getIsAbleToRushDelivery())
                .weight(cd.getWeight())
                .imageUrl(cd.getImageUrl())
                .barcodeUrl(cd.getBarcodeUrl())
                .description(cd.getDescription())
                .importDate(cd.getImportDate())

                .artists(cd.getArtists())
                .recordLabel(cd.getRecordLabel())
                .trackList(cd.getTrackList())
                .genres(cd.getGenres())
                .releaseDate(cd.getReleaseDate())
                .build();
    }
}
