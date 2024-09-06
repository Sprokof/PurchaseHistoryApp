package history.repository;

import history.entities.Operation;

public interface PurchaseHistoryRepository {
    boolean addOperation(Operation operation, long userId);
}
