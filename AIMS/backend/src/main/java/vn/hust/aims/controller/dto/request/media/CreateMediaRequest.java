package vn.hust.aims.controller.dto.request.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMediaRequest {
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
