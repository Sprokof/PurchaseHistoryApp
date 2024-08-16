package core.dto;

import java.time.LocalDate;

public class OperationDto {
    private double sum;
    private long userId;
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

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
