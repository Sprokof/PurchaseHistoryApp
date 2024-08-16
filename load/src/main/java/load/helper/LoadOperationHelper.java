package load.helper;

import core.dto.OperationDto;
import core.enums.OperationType;
import history.config.HistoryConfig;
import history.service.UserService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class LoadOperationHelper {

    public static final int COUNT_OPERATIONS = 10000;
    public static final int BATCH_SIZE = 100;
    private final UserService userService;

    public LoadOperationHelper(UserService userService) {
        this.userService = userService;
    }

    public OperationDto randomOperationDto() {
        long userId = userService.getRandomId();
        return new OperationDto(userId, getSum(), getType(), getDate());
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
}
