package vn.hust.aims.entity.media;

import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.enumeration.DiscFormat;

@Entity
@Table(name = "dvd")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper=true)
public class DVD extends Media {

  @Enumerated(EnumType.STRING)
  @Column(name = "disc_format")
  private DiscFormat discFormat;

  @Column(name = "director")
  private String director;

  @Column(name = "run_time")
  private Double runTime;

  @Column(name = "studio")
  private String studio;

  @Column(name = "language")
  private String language;

  @Column(name = "subtitles")
  private String subtitles;

  @Column(name = "genres")
  private String genres;

  @Column(name = "release_date")
  private LocalDate releaseDate;
}
