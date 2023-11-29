package vn.hust.aims.entity;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "cd")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CD extends Media {

  @Column(name = "artists")
  private String artists;

  @Column(name = "record_label")
  private String recordLabel;

  @Column(name = "track_list")
  private String trackList;

  @Column(name = "genres")
  private String genres;

  @Column(name = "release_date")
  private Instant releaseDate;
}
