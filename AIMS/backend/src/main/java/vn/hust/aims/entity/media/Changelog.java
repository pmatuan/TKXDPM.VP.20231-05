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

    @Column(name = "authorId")
    private Long authorId;

    @Column(name = "changedMediaId")
    private Long changedMediaId;

    @Column(name = "isPriceChange")
    private Integer isPriceChange;
}
