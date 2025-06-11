package eg.com.inventory.repository;

import eg.com.inventory.entity.Stock;
import eg.com.inventory.entity.StockId;
import eg.com.inventory.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepo extends JpaRepository<Stock, StockId> {
}
