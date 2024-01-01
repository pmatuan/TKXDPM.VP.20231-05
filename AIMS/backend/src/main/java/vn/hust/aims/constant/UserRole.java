package vn.hust.aims.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UserRole {
    public static final Set<String> roles = Collections.unmodifiableSet(
            new HashSet<String>(Arrays.asList(new String[]{"admin", "product manager"}))
    );
}
