/*
SOLID analysis:
 - Single Responsibility: OK, represent user entity in the system
 - Open - close principle: This class is not defined with extension in mind
 - Liskov substitution principle: This class is not defined for inheritance. However, in the future, potential violation of this principle may arise if different roles require different attributes
 - Interface segregation principle: This class does not provide methods and does not function as an interface
 - Dependency inversion principle: will be looked at on a project level
 */

package vn.hust.aims.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.constant.UserRole;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column(nullable = false)
    private String email;
    @Column
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    @Column(columnDefinition = "tinyint(1)")
    private Integer isBlocked;
    @Column
    private String role;

    @PrePersist
    void prePersist() {
        if (isBlocked == null) {
            isBlocked = 0;
        }
    }

    public Set<String> getUserRole() {
        return Collections.unmodifiableSet(
                new HashSet<String>(Arrays.asList(role.split(",")))
        );
    }

    public void setUserRole(Set<String> role) {
        this.role = String.join(",", role);
    }
}
