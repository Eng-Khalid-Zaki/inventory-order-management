package eg.com.inventory.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "order_status", nullable = false)
    private byte orderStatus;

    @Column(name = "order_date", updatable = false)
    private LocalDateTime orderDate;

    @Column(name = "required_date", nullable = false)
    private LocalDateTime requiredDate;

    @Column(name = "shipped_date")
    private LocalDateTime shippedDate;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @PrePersist
    protected void onCreate() {
        orderDate = LocalDateTime.now();
    }

    public Order() {}

    public Order(Customer customer, byte orderStatus, LocalDateTime requiredDate, Store store, Staff staff) {
        this.customer = customer;
        this.orderStatus = orderStatus;
        this.requiredDate = requiredDate;
        this.store = store;
        this.staff = staff;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public LocalDateTime getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDateTime requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer.getFirstName() + " " + customer.getLastName() +
                ", orderStatus=" + orderStatus +
                ", orderDate=" + orderDate +
                ", requiredDate=" + requiredDate +
                ", shippedDate=" + shippedDate +
                ", store=" + (store != null ? store.getStoreName() : "null") +
                ", staff=" + (staff != null ? staff.getId() : "null") +
                '}';
    }
}