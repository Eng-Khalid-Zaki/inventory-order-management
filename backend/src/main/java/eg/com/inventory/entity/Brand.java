package eg.com.inventory.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private int id;

    @Column(name = "brand_name", nullable = false, length = 50)
    private String brandName;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public Brand() {}

    public Brand(String brandName) {
        this.brandName = brandName;
    }

    public int getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}