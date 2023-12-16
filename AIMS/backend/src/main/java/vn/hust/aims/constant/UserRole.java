package vn.hust.aims.constant;

public enum UserRole {
    UNKNOWN("Unknown"),
    CUSTOMER("Customer"),
    SELLER("Seller"),
    ADMIN("Admin");

    private final String stringValue;

    UserRole(String stringValue) {
        this.stringValue = stringValue;
    }

    public static UserRole from(String stringValue) {
        for (UserRole role: UserRole.values()) {
            if (role.stringValue.equals(stringValue)) {
                return role;
            }
        }

        return UNKNOWN;
    }
}
