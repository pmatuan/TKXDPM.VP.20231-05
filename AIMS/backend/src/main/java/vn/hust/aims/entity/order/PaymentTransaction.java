package vn.hust.aims.entity.order;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.hust.aims.entity.TimedEntity;

@Entity
@Table(name = "payment_transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PaymentTransaction extends TimedEntity {

  @Id
  private String id;

  @Column(name = "payment_method")
  private String paymentMethod;

  @Column(name = "status")
  private Boolean status;

  @Column(name = "amount")
  private Double amount;

  @Column(name = "timestamp")
  private Instant timestamp;

  @Column(name = "content")
  private String content;

}
