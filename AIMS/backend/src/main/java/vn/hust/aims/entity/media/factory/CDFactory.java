package vn.hust.aims.entity.media.factory;

import vn.hust.aims.entity.media.CD;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.utils.JsonMapper;

@MediaFactoryProvider(value = "CD")
public class CDFactory implements MediaFactoryInterface {
    @Override
    public Media build(String jsonPayload) {
        return JsonMapper.convertJsonToObject(jsonPayload, CD.class);
    }
}
