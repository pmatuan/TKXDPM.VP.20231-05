package vn.hust.aims.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.aims.entity.order.RushOrder;

@Repository
public interface RushOrderRepository extends JpaRepository<RushOrder, String> {

}
