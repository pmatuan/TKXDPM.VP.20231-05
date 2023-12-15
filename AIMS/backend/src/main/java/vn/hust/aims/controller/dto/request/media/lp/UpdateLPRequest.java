package vn.hust.aims.controller.dto.request.media.lp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hust.aims.controller.dto.request.media.UpdateMediaRequest;
import vn.hust.aims.service.dto.input.media.lp.UpdateLPInput;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateLPRequest extends UpdateMediaRequest {
    private String artists;

    private String recordLabel;

    private String trackList;

    private String genres;

    private Instant releaseDate;

    public UpdateLPInput toInput(Long id) {

        return UpdateLPInput.builder()
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

                .artists(this.artists)
                .recordLabel(this.recordLabel)
                .trackList(this.trackList)
                .genres(this.genres)
                .releaseDate(this.releaseDate)
                .build();

    }
}
