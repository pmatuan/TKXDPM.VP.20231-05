package vn.hust.aims.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.TimedEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "rush_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RushOrder extends TimedEntity {

  @Id
  private String id;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @Column(name = "delivery_time")
  private Instant deliveryTime;

  @Column(name = "delivery_instruction")
  private String deliveryInstruction;
}
