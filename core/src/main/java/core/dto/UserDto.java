package core.dto;

import java.time.LocalDate;
import java.time.Period;

public class UserDto {
    private String username;
    private String password;
    private int age;
    private LocalDate birthDate;
    private LocalDate createdAt;

    public UserDto() {
        this.createdAt = LocalDate.now();
    }

    public UserDto(String username, String password, LocalDate birthDate) {
        this.username = username;
        this.password = password;
        this.createdAt = LocalDate.now();
        this.birthDate = birthDate;
        this.age = calculateAge();
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
}
