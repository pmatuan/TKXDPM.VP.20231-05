package vn.hust.aims.service.dto.output.media.book;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.media.Book;

@Data
@SuperBuilder
public class GetBookOutput {
    private Book book;

    public static GetBookOutput from(Book book) {

        return GetBookOutput
                .builder()
                .book(book)
                .build();
    }
}
