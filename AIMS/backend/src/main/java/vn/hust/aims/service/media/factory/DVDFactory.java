package vn.hust.aims.service.media.factory;

import vn.hust.aims.entity.media.Book;
import vn.hust.aims.entity.media.DVD;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.utils.JsonMapper;

public class DVDFactory implements MediaFactory {
    @Override
    public Media build(String jsonPayload) {
        return JsonMapper.convertJsonToObject(jsonPayload, DVD.class);
    }
}
