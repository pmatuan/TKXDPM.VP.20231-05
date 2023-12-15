package vn.hust.aims.controller.dto.response.media;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMediaResponse {
    protected String title;

    protected String category;

    protected Double value;

    protected Double price;

    protected Integer quantityInStock;

    protected Boolean isAbleToRushDelivery;

    protected Double weight;

    protected String imageUrl;

    protected String barcodeUrl;

    protected String description;

    protected Instant importDate;
}
