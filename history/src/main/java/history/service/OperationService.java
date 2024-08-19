package history.service;

import history.entities.Operation;
import history.entities.PurchaseHistory;
import history.entities.User;
import history.repository.OperationRepository;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class OperationService {

    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Operation create(Operation operation) {
        return this.operationRepository.create(operation);
    }
    public List<Operation> getAllByUserId(long userId) {
        return this.operationRepository.getAllByUserId(userId);
    }

}
