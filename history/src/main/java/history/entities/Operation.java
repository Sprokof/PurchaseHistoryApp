package history.entities;

import core.enums.OperationType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "operation")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
public class Operation extends BaseEntity {
    private double sum;
    private LocalDate date;
    @Enumerated(value = EnumType.STRING)
    private OperationType type;
    @ManyToOne
    @JoinColumn(name = "purchase_history_id")
    private PurchaseHistory purchaseHistory;

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
        if (purchaseHistory == null) return new PurchaseHistory();
        return purchaseHistory;
    }

    public void setPurchaseHistory(PurchaseHistory purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

}
