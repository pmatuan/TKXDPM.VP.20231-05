package vn.hust.aims.enumeration;

public enum MediaType {
    MEDIA("MEDIA"),
    BOOK("BOOK"),
    CD("CD"),
    DVD("DVD"),
    LP("LP"),
    UNKNOWN("UNKNOWN");

    private final String stringValue;

    MediaType(String stringValue) {
        this.stringValue = stringValue;
    }

    public static MediaType from(String value) {
        for (MediaType mediaType : MediaType.values()) {
            if (mediaType.stringValue.equals(value.toUpperCase())) {
                return mediaType;
            }
        }
        return UNKNOWN;
    }
}
