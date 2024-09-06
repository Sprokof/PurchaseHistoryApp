package history.service;

import core.dto.OperationDto;
import core.exceptions.UnknownOperationException;
import history.repository.PurchaseHistoryRepository;
import history.util.OperationUtil;
import org.springframework.stereotype.Service;

@Service
public class PurchaseHistoryService {
    private final PurchaseHistoryRepository purchaseHistoryRepository;
    public PurchaseHistoryService(PurchaseHistoryRepository purchaseHistoryRepository) {
        this.purchaseHistoryRepository = purchaseHistoryRepository;
    }

    public boolean addOperation(OperationDto operationDto) throws UnknownOperationException {
        return purchaseHistoryRepository.addOperation(OperationUtil.fromDto(operationDto), operationDto.getUserId());
    }
}
