package vn.hust.aims.service.dto.input.media;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
public class CreateMediaInput {
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
