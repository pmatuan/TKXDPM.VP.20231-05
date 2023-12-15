package vn.hust.aims.controller.media.book;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.hust.aims.controller.dto.request.media.book.CreateBookRequest;
import vn.hust.aims.controller.dto.request.media.book.UpdateBookRequest;
import vn.hust.aims.controller.dto.response.media.book.CreateBookResponse;
import vn.hust.aims.controller.dto.response.media.book.GetBookResponse;
import vn.hust.aims.controller.dto.response.media.book.UpdateBookResponse;
import vn.hust.aims.response.AimsCommonResponse;
import vn.hust.aims.service.dto.input.media.book.GetBookInput;
import vn.hust.aims.service.dto.output.media.book.CreateBookOutput;
import vn.hust.aims.service.dto.output.media.book.GetBookOutput;
import vn.hust.aims.service.dto.output.media.book.UpdateBookOutput;
import vn.hust.aims.service.media.book.BookService;
import vn.hust.aims.utils.ResponseUtil;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService;

    @PostMapping("")
    public ResponseEntity<AimsCommonResponse<Object>> createBook(@RequestBody CreateBookRequest createBookRequest) {
        CreateBookOutput output = bookService.createBook(createBookRequest.toInput());

        return ResponseUtil.toSuccessCommonResponse(
                CreateBookResponse.from(output)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AimsCommonResponse<Object>> getBook(@PathVariable Long id) {

        GetBookOutput getBookOutput = bookService.getBook(GetBookInput.builder().id(id).build());

        return ResponseUtil.toSuccessCommonResponse(
                GetBookResponse.from(getBookOutput)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AimsCommonResponse<Object>> updateBook(@PathVariable Long id, @RequestBody UpdateBookRequest updateBookRequest) {

        UpdateBookOutput updateBookOutput = bookService.updateBook(updateBookRequest.toInput(id));

        return ResponseUtil.toSuccessCommonResponse(
                UpdateBookResponse.from(updateBookOutput)
                );
    }
}
