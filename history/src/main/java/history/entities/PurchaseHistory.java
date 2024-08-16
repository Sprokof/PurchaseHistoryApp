package history.entities;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "purchase_history")
public class PurchaseHistory extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PurchaseHistory() {
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
