package core.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.Period;

//@Getter
//@Setter
//@ToString
public class UserDto {
    private long id;
    private String username;
    private String password;
    private int age;
    private LocalDate birthDate;
    private LocalDate createdAt;
    private double balance;

    public UserDto() {
        this.createdAt = LocalDate.now();
    }

    public UserDto(long id, String username, String password, LocalDate birthDate, double balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.balance = balance;
        this.age = calculateAge();
        this.createdAt = LocalDate.now();

    }

    private int calculateAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
