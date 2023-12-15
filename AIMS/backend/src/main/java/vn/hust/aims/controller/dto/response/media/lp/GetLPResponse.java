package vn.hust.aims.controller.dto.response.media.lp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.controller.dto.response.media.GetMediaResponse;
import vn.hust.aims.entity.media.LP;
import vn.hust.aims.service.dto.output.media.lp.GetLPOutput;

import javax.persistence.Column;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLPResponse extends GetMediaResponse {

    private String artists;
    
    private String recordLabel;

    private String trackList;
    
    private String genres;
    
    private Instant releaseDate;
    
    public static GetLPResponse from(GetLPOutput getLPOutput) {
        LP lp = getLPOutput.getLp();
        
        return GetLPResponse
                .builder()
                .title(lp.getTitle())
                .category(lp.getCategory())
                .value(lp.getValue())
                .price(lp.getPrice())
                .quantityInStock(lp.getQuantityInStock())
                .isAbleToRushDelivery(lp.getIsAbleToRushDelivery())
                .weight(lp.getWeight())
                .imageUrl(lp.getImageUrl())
                .barcodeUrl(lp.getBarcodeUrl())
                .description(lp.getDescription())

                .artists(lp.getArtists())
                .recordLabel(lp.getRecordLabel())
                .trackList(lp.getTrackList())
                .genres(lp.getGenres())
                .releaseDate(lp.getReleaseDate())
                .build();
    }
}
