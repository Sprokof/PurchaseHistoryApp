package history.entities;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "purchase_history_id")
    private List<Operation> operations;

    public void addOperation(Operation operation) {
        if (this.operations == null) {
            this.operations = new LinkedList<>();
        }
        this.operations.add(operation);
    }
}
