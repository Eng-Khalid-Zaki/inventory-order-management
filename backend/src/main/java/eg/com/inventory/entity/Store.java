package eg.com.inventory.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int id;

    @Column(name = "store_name", nullable = false, length = 50)
    private String storeName;

    @Column(name = "phone", length = 25, unique = true)
    private String phone;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column(name = "street", length = 50, nullable = false)
    private String street;

    @Column(name = "city", length = 25, nullable = false)
    private String city;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> stocks;

    public Store() {}

    public Store(String storeName, String phone, String email, String street, String city, String zipCode) {
        this.storeName = storeName;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}