package eg.com.inventory.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @EmbeddedId
    private OrderItemId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "list_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal listPrice;

    @Column(name = "discount", nullable = false, precision = 4, scale = 2)
    private BigDecimal discount;

    public OrderItem() {}

    public OrderItem(Order order, int itemId, Product product, int quantity, BigDecimal listPrice, BigDecimal discount) {
        this.id = new OrderItemId(order.getId(), itemId);
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.discount = discount;
    }

    public OrderItemId getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order=" + order.getId() +
                ", itemId=" + id.getItemId() +
                ", product=" + product.getProductName() +
                ", quantity=" + quantity +
                ", listPrice=" + listPrice +
                ", discount=" + discount +
                '}';
    }
}