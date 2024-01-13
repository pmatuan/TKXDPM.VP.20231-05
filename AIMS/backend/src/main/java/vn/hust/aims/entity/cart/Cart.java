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
import vn.hust.aims.entity.TimedEntity;


@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Cart extends TimedEntity {
  @Id
  private String id;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<CartMedia> cartMediaList;

  public void addCartMedia(CartMedia cartMedia) {
    cartMediaList.add(cartMedia);
    cartMedia.setCart(this);
  }

  public void removeCartMedia(CartMedia cartMedia) {
    cartMediaList.remove(cartMedia);
    cartMedia.setCart(null);
  }
}
