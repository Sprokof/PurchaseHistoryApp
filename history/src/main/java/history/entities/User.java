package history.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {
    @Column(name = "username", length = 20)
    private String username;
    @Column(name = "password", length = 20)
    private String password;
    @Column(name = "age")
    private int age;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "createdAt")
    private LocalDate createdAt;
    @Column(name = "balance")
    private double balance;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private PurchaseHistory purchaseHistory;

    public User() {
        this.createdAt = LocalDate.now();
    }

    public User(String username, String password, LocalDate birthDate, double balance) {
        this.username = username;
        this.password = password;
        this.createdAt = LocalDate.now();
        this.birthDate = birthDate;
        this.age = calculateAge();
        this.balance = balance;
    }
    private int calculateAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                ", createdAt=" + createdAt +
                '}';
    }

    public PurchaseHistory getPurchaseHistory() {
        if (purchaseHistory == null) {
            purchaseHistory = new PurchaseHistory();
            purchaseHistory.setUser(this);
        }
        return purchaseHistory;
    }
}
