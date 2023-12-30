package vn.hust.aims.repository.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.aims.entity.media.Changelog;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChangelogRepository extends JpaRepository<Changelog, Long> {
    public List<Changelog> findAllByChangedMediaIdAndTimestampAfterAndIsPriceChange(Long changedMediaId, Instant timestamp, Integer isPriceChange);
    public List<Changelog> findAllByAuthorIdAndTimestampAfter(Long authorId, Instant timestamp); // TODO: add change author id
}
