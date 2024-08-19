package history.entities;

import core.enums.OperationType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "operation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Operation extends BaseEntity {

    @Column(name = "sum")
    private double sum;

    @Column(name = "date")
    private LocalDate date;

    @Enumerated(value = EnumType.STRING)
    private OperationType type;

    @ManyToOne
    @JoinColumn(name = "purchase_history_id")
    private PurchaseHistory purchaseHistory;
}
