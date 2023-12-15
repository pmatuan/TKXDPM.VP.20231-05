package vn.hust.aims.service.media.factory;

import vn.hust.aims.entity.media.Book;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.utils.JsonMapper;

import java.lang.reflect.Field;
import java.util.Map;

public class BookFactory implements MediaFactory {
    @Override
    public Media build(String jsonPayload) {
        return JsonMapper.convertJsonToObject(jsonPayload, Book.class);
    }
}
