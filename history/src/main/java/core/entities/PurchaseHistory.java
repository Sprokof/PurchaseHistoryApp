package core.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    //it will contains list of 'purchase'
}
