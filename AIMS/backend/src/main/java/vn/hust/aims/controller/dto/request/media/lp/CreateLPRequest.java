package vn.hust.aims.controller.dto.request.media.lp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hust.aims.controller.dto.request.media.CreateMediaRequest;
import vn.hust.aims.service.dto.input.media.lp.CreateLPInput;

import javax.persistence.Column;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLPRequest extends CreateMediaRequest {

    private String artists;

    private String recordLabel;

    private String trackList;

    private String genres;

    private Instant releaseDate;

    public CreateLPInput toInput(){
        return CreateLPInput.builder()
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

                .artists(this.artists)
                .recordLabel(this.recordLabel)
                .trackList(this.trackList)
                .genres(this.genres)
                .releaseDate(this.releaseDate)
                .build();
    }
}
