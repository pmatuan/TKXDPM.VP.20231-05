package vn.hust.aims.repository.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.aims.entity.email.Sender;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Long> {
  Sender findByProvider(String provider);
}
