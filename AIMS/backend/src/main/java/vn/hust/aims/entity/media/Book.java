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
import vn.hust.aims.enumeration.CoverType;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper=true)
public class Book extends Media {

  @Column(name = "authors")
  private String authors;

  @Enumerated(EnumType.STRING)
  @Column(name = "cover_type")
  private CoverType coverType;

  @Column(name = "publisher")
  private String publisher;

  @Column(name = "publication_date")
  private LocalDate publicationDate;

  @Column(name = "pages")
  private Integer pages;

  @Column(name = "language")
  private String language;

  @Column(name = "type")
  private String type;
}
