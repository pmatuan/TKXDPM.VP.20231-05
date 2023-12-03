package vn.hust.aims.repository.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.aims.entity.media.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

}
