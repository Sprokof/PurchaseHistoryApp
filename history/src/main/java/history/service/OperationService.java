package history.service;

import history.entities.Operation;
import history.repository.OperationRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Operation create(Operation operation) {
        return this.operationRepository.create(operation);
    }
}
