package vn.hust.aims.controller.dto.request.media.cd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hust.aims.controller.dto.request.media.CreateMediaRequest;
import vn.hust.aims.service.dto.input.media.cd.CreateCDInput;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCDRequest extends CreateMediaRequest {

    private String artists;

    private String recordLabel;

    private String trackList;

    private String genres;

    private Instant releaseDate;

    public CreateCDInput toInput(){
        return CreateCDInput.builder()
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
