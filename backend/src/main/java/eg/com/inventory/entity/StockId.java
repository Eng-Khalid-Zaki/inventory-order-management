package eg.com.inventory.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StockId implements Serializable {

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "product_id")
    private int productId;

    public StockId() {}

    public StockId(int storeId, int productId) {
        this.storeId = storeId;
        this.productId = productId;
    }

    public int getStoreId() {
        return storeId;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StockId stockId = (StockId) obj;
        return storeId == stockId.storeId && productId == stockId.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, productId);
    }
}