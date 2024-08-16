package history.repository;

import history.entities.User;

public interface UserRepository {
    User create(User user);
    void update(User user);
    User getById(long id);
    long getRandomId();

    User getWithPurchaseHistoryAndOperations(long id);

}
