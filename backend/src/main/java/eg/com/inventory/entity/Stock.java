package eg.com.inventory.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {

    @EmbeddedId
    private StockId id;

    @ManyToOne
    @MapsId("storeId")
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public void setId(StockId id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Column(nullable = false)
    private int quantity;

    public Stock() {}

    public Stock(Store store, Product product, int quantity) {
        this.id = new StockId(store.getId(), product.getId());
        this.store = store;
        this.product = product;
        this.quantity = quantity;
    }

    public StockId getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "store=" + store.getStoreName() +
                ", product=" + product.getProductName() +
                ", quantity=" + quantity +
                '}';
    }
}