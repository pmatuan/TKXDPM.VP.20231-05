package vn.hust.aims.entity.media.factory;

import vn.hust.aims.entity.media.DVD;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.utils.JsonMapper;

@MediaFactoryProvider(value = "DVD")
public class DVDFactory implements MediaFactoryInterface {
    @Override
    public Media build(String jsonPayload) {
        return JsonMapper.convertJsonToObject(jsonPayload, DVD.class);
    }
}
