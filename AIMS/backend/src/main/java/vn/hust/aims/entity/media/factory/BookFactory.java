package vn.hust.aims.entity.media.factory;

import vn.hust.aims.entity.media.Book;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.utils.JsonMapper;

@MediaFactoryProvider(value = "BOOK")
public class BookFactory implements MediaFactoryInterface {
    @Override
    public Media build(String jsonPayload) {
        return JsonMapper.convertJsonToObject(jsonPayload, Book.class);
    }
}
