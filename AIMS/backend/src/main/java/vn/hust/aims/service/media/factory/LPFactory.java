package vn.hust.aims.service.media.factory;

import vn.hust.aims.entity.media.LP;
import vn.hust.aims.entity.media.Media;
import vn.hust.aims.utils.JsonMapper;

public class LPFactory implements MediaFactory {
    @Override
    public Media build(String jsonPayload) {
        return JsonMapper.convertJsonToObject(jsonPayload, LP.class);
    }
}
