package eg.com.inventory.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemId implements Serializable {

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "item_id")
    private int itemId;

    public OrderItemId() {}

    public OrderItemId(int orderId, int itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getItemId() {
        return itemId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderItemId that = (OrderItemId) obj;
        return orderId == that.orderId && itemId == that.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, itemId);
    }
}