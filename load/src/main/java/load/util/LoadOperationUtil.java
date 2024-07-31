package load.util;

import core.dto.OperationDto;
import core.enums.OperationType;

import java.time.LocalDate;

public class LoadOperationUtil {
    public static final int COUNT_OPERATIONS = 3000;
    public static final int COUNT_OPERATIONS_PER_SECOND = 100;

    private LoadOperationUtil() {}

    public static OperationDto randomOperationDto() {
        return new OperationDto(getSum(), getType(), getDate());
    }

    private static Double getSum() {
        return Math.random();
    }

    public static String getType() {
        OperationType[] types = OperationType.values();
        int index = (int) (Math.random() * types.length);
        return types[index].getValue();
    }

    private static LocalDate getDate() {
        return LocalDate.now();
    }
}
