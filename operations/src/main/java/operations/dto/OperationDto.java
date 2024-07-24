package operations.dto;

import java.time.LocalDate;

public class OperationDto {
    private double sum;
    private String type;
    private LocalDate date;
    public OperationDto() {
    }

    public OperationDto(double sum, String type, LocalDate date) {
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
}
