package vn.hust.aims.service.media.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.media.Book;
import vn.hust.aims.enumeration.CoverType;
import vn.hust.aims.repository.media.BookRepository;
import vn.hust.aims.service.dto.input.media.book.CreateBookInput;
import vn.hust.aims.service.dto.input.media.book.GetBookInput;
import vn.hust.aims.service.dto.input.media.book.UpdateBookInput;
import vn.hust.aims.service.dto.output.media.book.CreateBookOutput;
import vn.hust.aims.service.dto.output.media.book.GetBookOutput;
import vn.hust.aims.service.dto.output.media.book.UpdateBookOutput;

import java.util.Optional;


@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public CreateBookOutput createBook(CreateBookInput createBookInput) {
        Book book = Book.builder()
                .title(createBookInput.getTitle())
                .category(createBookInput.getCategory())
                .value(createBookInput.getValue())
                .price(createBookInput.getPrice())
                .quantityInStock(createBookInput.getQuantityInStock())
                .isAbleToRushDelivery(createBookInput.getIsAbleToRushDelivery())
                .weight(createBookInput.getWeight())
                .imageUrl(createBookInput.getImageUrl())
                .barcodeUrl(createBookInput.getBarcodeUrl())
                .description(createBookInput.getDescription())
                .importDate(createBookInput.getImportDate())

                .authors(createBookInput.getAuthors())
                .coverType(createBookInput.getCoverType())
                .publisher(createBookInput.getPublisher())
                .publicationDate(createBookInput.getPublicationDate())
                .pages(createBookInput.getPages())
                .language(createBookInput.getLanguage())
                .type(createBookInput.getType())
                .build();

        bookRepository.save(book);

        return CreateBookOutput.from("Create book success");
    }

    public GetBookOutput getBook(GetBookInput getBookInput) {
        Optional<Book> book = bookRepository.findById(getBookInput.getId());

        return GetBookOutput
                .builder().book(book.orElseThrow()).build();

    }

    public UpdateBookOutput updateBook(UpdateBookInput updateBookInput) {
        System.out.println(updateBookInput);

        Book book = bookRepository.findById(updateBookInput.getId()).orElseThrow();

        book.setTitle(updateBookInput.getTitle());
        book.setCategory(updateBookInput.getCategory());
        book.setValue(updateBookInput.getValue());
        book.setPrice(updateBookInput.getPrice());
        book.setQuantityInStock(updateBookInput.getQuantityInStock());
        book.setIsAbleToRushDelivery(updateBookInput.getIsAbleToRushDelivery());
        book.setWeight(updateBookInput.getWeight());
        book.setImageUrl(updateBookInput.getImageUrl());
        book.setBarcodeUrl(updateBookInput.getBarcodeUrl());
        book.setDescription(updateBookInput.getDescription());
        book.setImportDate(updateBookInput.getImportDate());

        book.setAuthors(updateBookInput.getAuthors());
        book.setCoverType(updateBookInput.getCoverType());
        book.setPublisher(updateBookInput.getPublisher());
        book.setPublicationDate(updateBookInput.getPublicationDate());
        book.setPages(updateBookInput.getPages());
        book.setLanguage(updateBookInput.getLanguage());
        book.setType(updateBookInput.getType());


        bookRepository.save(book);

        return UpdateBookOutput.from("Update success");
    }
}
