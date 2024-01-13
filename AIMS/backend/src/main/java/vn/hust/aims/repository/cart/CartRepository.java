package vn.hust.aims.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.aims.entity.cart.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

}
