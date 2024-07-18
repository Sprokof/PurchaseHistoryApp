package operations.dto;

import java.time.LocalDate;
import java.time.Period;

public class UserDto {
    private String username;
    private String password;
    private LocalDate birthDate;
    private int age;

    public UserDto(String username, String password, LocalDate birthDate) {
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.age = calculateAge();
    }

    public UserDto() {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    private int calculateAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

}
