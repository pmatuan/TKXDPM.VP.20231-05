package vn.hust.aims.entity.media.factory;

import vn.hust.aims.entity.media.Media;

public interface MediaFactoryInterface {
    Media build(String mediaMap);
}
