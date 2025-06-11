package eg.com.inventory.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "staffs")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int id;

    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "phone", length = 15, unique = true)
    private String phone;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Staff manager;

    public Staff() {}

    public Staff(String firstName, String lastName, String email, String phone, boolean active, Store store, Staff manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.active = active;
        this.store = store;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Staff getManager() {
        return manager;
    }

    public void setManager(Staff manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                ", store=" + (store != null ? store.getStoreName() : "null") +
                ", manager=" + (manager != null ? manager.getFirstName() + " " + manager.getLastName() : "null") +
                '}';
    }
}