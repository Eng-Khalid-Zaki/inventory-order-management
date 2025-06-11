package eg.com.inventory.dto;

import java.util.List;

public class BrandDTO {
    private int id;
    private String brandName;
    private List<ProductDTO> products; // List of products under this brand

    public BrandDTO() {}

    public BrandDTO(int id, String brandName, List<ProductDTO> products) {
        this.id = id;
        this.brandName = brandName;
        this.products = products;
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

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "BrandDTO{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", products=" + products +
                '}';
    }
}