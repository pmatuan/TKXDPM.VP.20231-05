package vn.hust.aims.entity;

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

@Entity
@Table(name = "payment_transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PaymentTransaction {

  @Id
  private String id;

  @Column(name = "amount")
  private Double amount;

  @Column(name = "timestamp")
  private Instant timestamp;

  @Column(name = "content")
  private String content;

  @CreationTimestamp
  @Column(name = "created_at")
  private Instant createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Instant updatedAt;

}
