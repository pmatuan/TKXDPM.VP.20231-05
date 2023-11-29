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
@Table(name = "rush_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RushOrder extends Order {

  @Column(name = "delivery_time")
  private Instant deliveryTime;

  @Column(name = "delivery_instruction")
  private String deliveryInstruction;
}
