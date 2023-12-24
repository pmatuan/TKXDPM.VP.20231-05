package vn.hust.aims.service.media.factory;

import vn.hust.aims.entity.media.Media;

public interface MediaFactoryInterface {
    Media build(String mediaMap);
}
