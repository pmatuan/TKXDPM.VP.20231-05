package vn.hust.aims.service.media.factory;


import vn.hust.aims.enumeration.MediaType;

import static vn.hust.aims.enumeration.MediaType.*;

public class MediaFactoryBuilder {
    public static MediaFactory get(MediaType mediaType) {
        return switch (mediaType) {
            case BOOK -> new BookFactory();
            case CD -> new CDFactory();
            case DVD -> new DVDFactory();
            case LP -> new LPFactory();
            default -> null;
        };
    }


}
