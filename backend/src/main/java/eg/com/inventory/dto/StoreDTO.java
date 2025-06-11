package eg.com.inventory.dto;

import eg.com.inventory.entity.Stock;
import jakarta.persistence.*;

import java.util.List;

public class StoreDTO {
    private int id;
    private String storeName;
    private String phone;
    private String email;
    private String street;
    private String city;
    private String zipCode;
    private List<StockDTO> stocks;

    public StoreDTO() {
    }

    public StoreDTO(int id, String storeName, String phone, String email, String street, String city, String zipCode, List<StockDTO> stocks) {
        this.id = id;
        this.storeName = storeName;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.stocks = stocks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<StockDTO> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockDTO> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "StoreDTO{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", stocks=" + stocks +
                '}';
    }
}
