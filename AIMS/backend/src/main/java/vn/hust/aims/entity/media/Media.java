package vn.hust.aims.entity.media;

import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.hust.aims.entity.TimedEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "media")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Media extends TimedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Column(name = "title")
  protected String title;

  @Column(name = "category")
  protected String category;

  @Column(name = "value")
  protected Double value;

  @Column(name = "price")
  protected Double price;

  @Column(name = "quantity_in_stock")
  protected Integer quantityInStock;

  @Column(name = "is_able_to_rush_delivery")
  protected Boolean isAbleToRushDelivery;

  @Column(name = "weight")
  protected Double weight;

  @Column(name = "image_url", columnDefinition = "text")
  protected String imageUrl;

  @Column(name = "barcodeUrl")
  protected String barcodeUrl;

  @Column(name = "description")
  protected String description;

  @Column(name = "import_date")
  protected LocalDate importDate;

}
