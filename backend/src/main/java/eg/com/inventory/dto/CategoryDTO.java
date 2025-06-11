package eg.com.inventory.dto;

import java.util.List;

public class CategoryDTO {
    private int id;
    private String categoryName;
    private List<ProductDTO> products; // List of products under this category

    public CategoryDTO() {}

    public CategoryDTO(int id, String categoryName, List<ProductDTO> products) {
        this.id = id;
        this.categoryName = categoryName;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", products=" + products +
                '}';
    }
}