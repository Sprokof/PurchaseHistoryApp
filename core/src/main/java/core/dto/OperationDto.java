package core.dto;

import lombok.*;

import java.time.LocalDate;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Setter
public class OperationDto {
    private long userId;
    private double sum;
    private String type;
    private LocalDate date;

    public OperationDto() {
    }

    public OperationDto(long userId, double sum, String type, LocalDate date) {
        this.userId = userId;
        this.sum = sum;
        this.type = type;
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
