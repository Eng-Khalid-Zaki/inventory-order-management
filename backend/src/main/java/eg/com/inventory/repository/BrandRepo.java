package eg.com.inventory.repository;

import eg.com.inventory.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer> {
    Brand findByBrandName(String name);
}
