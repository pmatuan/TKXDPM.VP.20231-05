package vn.hust.aims.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.hust.aims.entity.TimedEntity;
import vn.hust.aims.entity.cart.CartMedia;
import vn.hust.aims.entity.media.Media;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "order_media")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderMedia extends TimedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "media_id")
  private Media media;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "is_order_for_rush_delivery")
  private Boolean isOrderForRushDelivery;

  public static OrderMedia from(CartMedia cartMedia) {
    return OrderMedia.builder()
        .media(cartMedia.getMedia())
        .quantity(cartMedia.getQuantity())
        .isOrderForRushDelivery(false)
        .build();
  }

}
