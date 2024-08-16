package history.util;

import core.dto.OperationDto;
import core.enums.OperationType;
import core.exceptions.UnknownOperationException;
import history.entities.Operation;

public class OperationUtil {
    private OperationUtil() {}

    public static Operation fromDto(OperationDto dto) throws UnknownOperationException {
        Operation operation = new Operation();
        operation.setSum(dto.getSum());
        operation.setDate(dto.getDate());
        operation.setType(OperationType.ignoreCaseValueOf(dto.getType()));
        return operation;
    }
}
