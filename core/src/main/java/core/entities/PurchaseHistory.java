package core.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase_history")
public class PurchaseHistory extends BaseEntity {

    @OneToOne(mappedBy = "user")
    private User user;

    public PurchaseHistory() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //it will contains list of 'purchase'
}
