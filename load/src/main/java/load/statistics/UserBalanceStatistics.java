package load.statistics;

import core.dto.OperationDto;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserBalanceStatistics {
    private static final Map<Long, Double> usersStatistics = new ConcurrentHashMap<>();

    public static void addOperation(OperationDto dto) {
        long userId = dto.getUserId();
        double sum = dto.getSum();
        usersStatistics.put(userId, (usersStatistics.getOrDefault(userId, 0d) + sum));
    }

    public static String get(Long userId) {
        return String.format("Operations sum: %s for user with id: %d", usersStatistics.get(userId), userId);
    }

    public static String getAll() {
        return usersStatistics.toString();
    }
}
