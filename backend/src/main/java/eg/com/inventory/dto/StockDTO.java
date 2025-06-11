package eg.com.inventory.dto;

import eg.com.inventory.entity.Product;
import eg.com.inventory.entity.StockId;
import eg.com.inventory.entity.Store;

public class StockDTO {
    private StockId id;
    private int storeId;
    private int productId;
    private int quantity;

    public StockDTO() {
    }

    public StockDTO(StockId id, int storeId, int productId, int quantity) {
        this.id = id;
        this.storeId = storeId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public StockId getId() {
        return id;
    }

    public void setId(StockId id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
