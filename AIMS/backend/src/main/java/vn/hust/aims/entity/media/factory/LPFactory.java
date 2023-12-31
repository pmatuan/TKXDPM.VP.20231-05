package vn.hust.aims.entity.media.factory;

import vn.hust.aims.entity.media.LP;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.utils.JsonMapper;

@MediaFactoryProvider(value = "LP")
public class LPFactory implements MediaFactoryInterface {
    @Override
    public Media build(String jsonPayload) {
        return JsonMapper.convertJsonToObject(jsonPayload, LP.class);
    }
}
