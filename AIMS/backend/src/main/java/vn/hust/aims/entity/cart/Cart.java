package vn.hust.aims.entity.cart;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Cart {
  @Id
  private String id;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<CartMedia> cartMediaList;
}
