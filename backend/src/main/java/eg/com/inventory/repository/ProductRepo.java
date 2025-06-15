package eg.com.inventory.repository;

import eg.com.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(int id);
    List<Product> findByBrandId(int id);
}
