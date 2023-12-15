package vn.hust.aims.controller.dto.request.media.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hust.aims.controller.dto.request.media.CreateMediaRequest;
import vn.hust.aims.enumeration.CoverType;
import vn.hust.aims.service.dto.input.media.book.CreateBookInput;


import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest extends CreateMediaRequest {

    private String authors;

    private String coverType;

    private String publisher;

    private Instant publicationDate;

    private Integer pages;

    private String language;

    private String type;


    public CreateBookInput toInput() {
        return CreateBookInput.builder()
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

                .authors(this.authors)
                .coverType(CoverType.from(this.coverType))
                .publisher(this.publisher)
                .publicationDate(this.publicationDate)
                .pages(this.pages)
                .language(this.language)
                .type(this.type)
                .build();
    }
}
