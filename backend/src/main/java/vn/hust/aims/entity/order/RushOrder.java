package vn.hust.aims.entity.order;

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
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "rush_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RushOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @Column(name = "delivery_time")
  private Instant deliveryTime;

  @Column(name = "delivery_instruction")
  private String deliveryInstruction;
}
