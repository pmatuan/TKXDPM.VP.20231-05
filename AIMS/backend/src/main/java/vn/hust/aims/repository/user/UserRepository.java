package vn.hust.aims.repository.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.aims.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @NotNull Page<User> findAll(Pageable pageable);
}
