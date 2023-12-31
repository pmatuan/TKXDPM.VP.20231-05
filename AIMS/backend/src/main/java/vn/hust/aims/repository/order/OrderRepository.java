package vn.hust.aims.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.hust.aims.entity.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
  @Query("""
      select o
      from Order o
      order by o.createdAt desc
    """)
  Page<Order> getAllOrderPage(Pageable pageable);
}
