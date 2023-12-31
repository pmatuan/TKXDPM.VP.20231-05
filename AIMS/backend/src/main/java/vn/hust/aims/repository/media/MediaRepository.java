package vn.hust.aims.repository.media;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.aims.entity.media.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    Page<Media> findAllByTitleContaining(String title, Pageable pageable);
}
