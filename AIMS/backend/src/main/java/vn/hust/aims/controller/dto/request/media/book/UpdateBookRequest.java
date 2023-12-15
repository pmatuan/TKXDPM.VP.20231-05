package vn.hust.aims.controller.dto.request.media.book;


import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hust.aims.controller.dto.request.media.UpdateMediaRequest;
import vn.hust.aims.enumeration.CoverType;
import vn.hust.aims.service.dto.input.media.book.UpdateBookInput;

import java.time.Instant;


@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateBookRequest extends UpdateMediaRequest {
    private String authors;

    private String coverType;

    private String publisher;

    private Instant publicationDate;

    private Integer pages;

    private String language;

    private String type;

    public UpdateBookInput toInput(Long id) {

        return UpdateBookInput.builder()
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
