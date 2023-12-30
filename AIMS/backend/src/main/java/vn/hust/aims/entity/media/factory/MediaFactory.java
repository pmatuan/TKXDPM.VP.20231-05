package vn.hust.aims.entity.media.factory;

import vn.hust.aims.entity.media.Media;
import vn.hust.aims.utils.JsonMapper;

@MediaFactoryProvider(value = "MEDIA")
public class MediaFactory implements MediaFactoryInterface {
    @Override
    public Media build(String jsonPayload) {
        return JsonMapper.convertJsonToObject(jsonPayload, Media.class);
    }
}
