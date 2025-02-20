package history.entities;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase_history")
@NoArgsConstructor
@Getter
@Setter
public class PurchaseHistory extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = {CascadeType.ALL})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "purchase_history_id")
    private List<Operation> operations;


    public void addOperation(Operation operation) {
        if (this.operations == null) {
            this.operations = new ArrayList<>();
        }
        double currentBalance = this.user.getBalance();
        currentBalance -= operation.getSum();
        this.user.setBalance(currentBalance);
        this.operations.add(operation);
        operation.setPurchaseHistory(this);
    }

    public List<Operation> getOperations() {
        if (this.operations == null) {
            return new ArrayList<>();
        }
        return operations;
    }

    @Override
    public String toString() {
        return "PurchaseHistory{" +
                "id=" + id +
                '}';
    }
}
