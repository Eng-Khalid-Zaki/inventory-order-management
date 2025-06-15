package eg.com.inventory.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private int id;
    private int customerId;
    private String customerName; // Combining first & last name for convenience
    private byte orderStatus;
    private LocalDateTime orderDate;
    private LocalDateTime requiredDate;
    private LocalDateTime shippedDate;
    private int storeId;
    private String storeName;
    private int staffId;

    public OrderDTO() {}

    public OrderDTO(int id, int customerId, String customerName, byte orderStatus,
                    LocalDateTime orderDate, LocalDateTime requiredDate, LocalDateTime shippedDate,
                    int storeId, String storeName, int staffId) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.storeId = storeId;
        this.storeName = storeName;
        this.staffId = staffId;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
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

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderDate=" + orderDate +
                ", requiredDate=" + requiredDate +
                ", shippedDate=" + shippedDate +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", staffId=" + staffId +
                '}';
    }
}