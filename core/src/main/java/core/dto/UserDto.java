package core.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@ToString
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

    }

    private int calculateAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }


}
