package generator.service;

import core.dto.UserDto;
import generator.jdbc.UserJdbcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateUserService {
    private static final String USERNAME_PREFIX = "username_";
    private static final int USERS_COUNT = 2000;

    private static final double START_BALANCE = 10000.00;
    private static final Logger log = LoggerFactory.getLogger(GenerateUserService.class);

    private final UserJdbcRepository userRepository;

    public GenerateUserService(UserJdbcRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run()  {
          for (int i = 0; i < USERS_COUNT; i ++) {
              String username = USERNAME_PREFIX + (i + 1);
              String password = PasswordGenerator.generate();
              LocalDate birthDate = DateGenerator.generate();
              UserDto userDto = new UserDto(username, password, birthDate, START_BALANCE);
              log.info("save user {}" , userDto);
              boolean saved = userRepository.save(new UserDto(username, password, birthDate, START_BALANCE));
              log.info("user {} saved:{}", userDto, saved);
          }
    }

    private static class PasswordGenerator {
        private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
        private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String DIGITS = "0123456789";
        private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";

        static String generate() {
            int length = (int) (Math.random() * 20);
            StringBuilder password = new StringBuilder(length);
            Random random = new Random(System.nanoTime());

            List<String> charCategories = new ArrayList<>(4);
            charCategories.add(LOWER);
            charCategories.add(UPPER);
            charCategories.add(DIGITS);
            charCategories.add(PUNCTUATION);

            for (int i = 0; i < length; i++) {
                String charCategory = charCategories.get(random.nextInt(charCategories.size()));
                int position = random.nextInt(charCategory.length());
                password.append(charCategory.charAt(position));
            }
            return password.toString();
        }
    }

    private static class DateGenerator {
        private static final LocalDate CURRENT_DATE = LocalDate.now();
        private static final int YEARS_RANGE = 70;
        private static final int MOTHS_RANGE = 12;
        private static final int DAYS_RANGE = 30;

        static LocalDate generate() {
            int countYears = (int) (Math.random() * YEARS_RANGE);
            int countMonths = (int) (Math.random() * MOTHS_RANGE);
            int countDays = (int) (Math.random() * DAYS_RANGE);
            return CURRENT_DATE.minusYears(countYears)
                    .minusMonths(countMonths).minusDays(countDays);
        }
    }
}
