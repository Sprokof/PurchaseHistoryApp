package load.helper;

import core.dto.OperationDto;
import core.enums.OperationType;
import core.util.UserUtil;
import history.config.HistoryConfig;
import history.service.UserService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class LoadOperationHelper {
    public static final int COUNT_OPERATIONS = 10000;
    public static final int BATCH_SIZE = 100;
    public LoadOperationHelper() {
    }

    public OperationDto randomOperationDto() {
        return new OperationDto(getRandomUserId(), getSum(), getType(), getDate());
    }

    private Double getSum() {
        return Math.random() * (Math.random() * 100);
    }

    private String getType() {
        OperationType[] types = OperationType.values();
        int index = (int) (Math.random() * types.length);
        return types[index].getValue();
    }

    private LocalDate getDate() {
        return LocalDate.now();
    }

    private long getRandomUserId() {
        long id = (long) (Math.random() * UserUtil.USERS_COUNT);
        return id == 0 ? 1 : id;
    }
}
