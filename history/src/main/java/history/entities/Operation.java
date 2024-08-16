package history.entities;

import core.enums.OperationType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "operation")
public class Operation extends BaseEntity {
    private double sum;
    private LocalDate date;
    @Enumerated(value = EnumType.STRING)
    private OperationType type;
    @ManyToOne
    @JoinColumn(name = "purchase_history_id")
    public PurchaseHistory purchaseHistory;

    public Operation() {}
    public Operation(double sum, LocalDate date, OperationType type) {
        this.sum = sum;
        this.date = date;
        this.type = type;
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

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public PurchaseHistory getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(PurchaseHistory purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "sum=" + sum +
                ", date=" + date +
                ", type=" + type +
                '}';
    }
}
