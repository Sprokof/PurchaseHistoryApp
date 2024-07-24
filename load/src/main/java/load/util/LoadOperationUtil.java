package load.util;

import history.entities.enums.OperationType;

import java.time.LocalDate;

public class LoadOperationUtil {
    public static final int COUNT_OPERATIONS = 3000;
    public static final int COUNT_OPERATIONS_PER_SECOND = 100;

    private LoadOperationUtil() {}

    public static String getPriceQueryParam() {
        return String.valueOf(Math.random());
    }

    public static String getTypeQueryParam() {
        OperationType[] types = OperationType.values();
        int index = (int) (Math.random() * types.length);
        return types[index].getValue();
    }

    public static String getDateQueryParam() {
        return LocalDate.now().toString();
    }
}
