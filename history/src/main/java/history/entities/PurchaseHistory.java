package history.entities;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "purchase_history")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PurchaseHistory extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = {CascadeType.ALL})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "purchase_history_id")
    private List<Operation> operations;

    public void addOperation(Operation operation) {
        if (this.operations == null) {
            this.operations = new LinkedList<>();
        }
        double current = user.getBalance();
        current -= operation.getSum();
        user.setBalance(current);
        this.operations.add(operation);
        operation.setPurchaseHistory(this);
    }
}
