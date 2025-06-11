package eg.com.inventory.dto;

import java.math.BigDecimal;

public class ProductDTO {
    private int id;
    private String productName;
    private int brandId;
    private int categoryId;
    private short modelYear;
    private BigDecimal listPrice;

    public ProductDTO() {}

    public ProductDTO(int id, String productName, int brandId, int categoryId, short modelYear, BigDecimal listPrice) {
        this.id = id;
        this.productName = productName;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public short getModelYear() {
        return modelYear;
    }

    public void setModelYear(short modelYear) {
        this.modelYear = modelYear;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", brandId=" + brandId +
                ", categoryId=" + categoryId +
                ", modelYear=" + modelYear +
                ", listPrice=" + listPrice +
                '}';
    }
}