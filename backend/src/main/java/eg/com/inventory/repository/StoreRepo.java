package eg.com.inventory.repository;

import eg.com.inventory.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepo extends JpaRepository<Store, Integer> {
}
