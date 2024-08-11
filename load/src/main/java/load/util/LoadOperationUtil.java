package load.util;

import core.dto.OperationDto;
import core.enums.OperationType;

import java.time.LocalDate;

public class LoadOperationUtil {
    public static final int COUNT_OPERATIONS = 10000;
    public static final int BATCH_SIZE = 100;

    private LoadOperationUtil() {}

    public static OperationDto randomOperationDto() {
        return new OperationDto(getSum(), getType(), getDate());
    }

    private static Double getSum() {
        return Math.random() * (Math.random() * 10);
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
