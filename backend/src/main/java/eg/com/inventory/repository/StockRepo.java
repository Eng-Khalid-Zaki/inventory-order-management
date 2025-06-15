package eg.com.inventory.repository;

import eg.com.inventory.dto.StockDTO;
import eg.com.inventory.entity.Stock;
import eg.com.inventory.entity.StockId;
import eg.com.inventory.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepo extends JpaRepository<Stock, StockId> {
    List<Stock> getStocksByStoreId(int storeId);
}
