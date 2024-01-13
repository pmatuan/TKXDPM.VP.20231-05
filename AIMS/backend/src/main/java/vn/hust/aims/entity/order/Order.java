package vn.hust.aims.entity.order;

import java.time.Instant;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.hust.aims.entity.TimedEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "`order`")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Order extends TimedEntity {

  @Id
  private String id;

  @OneToOne
  @JoinColumn(name = "transaction_id")
  private PaymentTransaction paymentTransaction;

  @OneToOne
  @JoinColumn(name = "delivery_info_id")
  private DeliveryInfo deliveryInfo;

  @OneToOne(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private RushOrder rushOrder;

  @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<OrderMedia> orderMediaList;

  @Column(name = "state")
  private String state;

  @Column(name = "subtotal")
  private Double subtotal;

  @Column(name = "delivery_fee")
  private Double deliveryFee;

  @Column(name = "vat")
  private Double vat;

  @Column(name = "total")
  private Double total;
}
