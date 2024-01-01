package vn.hust.aims.entity.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "changelog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Changelog {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "timestamp")
    private Instant timestamp;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "changed_media_id")
    private Long changedMediaId;

    @Column(name = "is_price_change")
    private Integer isPriceChange;
}
