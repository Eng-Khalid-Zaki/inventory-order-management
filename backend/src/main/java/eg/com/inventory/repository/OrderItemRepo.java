package eg.com.inventory.repository;

import eg.com.inventory.entity.OrderItem;
import eg.com.inventory.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, OrderItemId> {
}
