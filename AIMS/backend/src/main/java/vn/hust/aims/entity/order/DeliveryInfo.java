package vn.hust.aims.entity.order;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "delivery_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DeliveryInfo extends TimedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "email")
  private String email;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "province")
  private String province;

  @Column(name = "address")
  private String address;

}
