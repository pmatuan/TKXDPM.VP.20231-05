package vn.hust.aims.entity.media;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "lp")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper=true)
public class LP extends Media {

  @Column(name = "artists")
  private String artists;

  @Column(name = "record_label")
  private String recordLabel;

  @Column(name = "track_list")
  private String trackList;

  @Column(name = "genres")
  private String genres;

  @Column(name = "release_date")
  private LocalDate releaseDate;
}
