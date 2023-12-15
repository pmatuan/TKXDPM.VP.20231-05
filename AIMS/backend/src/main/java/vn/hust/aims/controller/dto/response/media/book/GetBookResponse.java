package vn.hust.aims.controller.dto.response.media.book;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.controller.dto.response.media.GetMediaResponse;
import vn.hust.aims.entity.media.Book;
import vn.hust.aims.service.dto.output.media.book.GetBookOutput;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBookResponse extends GetMediaResponse {
    private String authors;

    private String coverType;

    private String publisher;

    private Instant publicationDate;

    private Integer pages;

    private String language;

    private String type;

    public static GetBookResponse from(GetBookOutput getBookOutput) {
        Book book = getBookOutput.getBook();

        return GetBookResponse
                .builder()
                .title(book.getTitle())
                .category(book.getCategory())
                .value(book.getValue())
                .price(book.getPrice())
                .quantityInStock(book.getQuantityInStock())
                .isAbleToRushDelivery(book.getIsAbleToRushDelivery())
                .weight(book.getWeight())
                .imageUrl(book.getImageUrl())
                .barcodeUrl(book.getBarcodeUrl())
                .description(book.getDescription())
                .importDate(book.getImportDate())

                .authors(book.getAuthors())
                .coverType(book.getCoverType().toString())
                .publisher(book.getPublisher())
                .publicationDate(book.getPublicationDate())
                .pages(book.getPages())
                .language(book.getLanguage())
                .type(book.getType())
                .build();
    }

}
