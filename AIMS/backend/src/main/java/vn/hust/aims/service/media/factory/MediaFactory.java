package vn.hust.aims.service.media.factory;

import vn.hust.aims.entity.media.Media;

public interface MediaFactory {
    public Media build(String mediaMap);
}
