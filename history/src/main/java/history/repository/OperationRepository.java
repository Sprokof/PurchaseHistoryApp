package history.repository;

import history.entities.Operation;

import java.util.List;

public interface OperationRepository {
    Operation create(Operation operation);
    List<Operation> getOperationsByUserId(long userId);
}
