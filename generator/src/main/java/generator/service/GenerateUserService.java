package generator.service;

import history.entities.User;
import history.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GenerateUserService implements CommandLineRunner {

    private static final String USERNAME_PREFIX = "username_";
    private static final int USERS_COUNT = 1000;
    private final UserService userService;

    public GenerateUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
          for (int i = 0; i < USERS_COUNT; i ++) {
              String username = USERNAME_PREFIX + (i + 1);
              String password = PasswordGenerator.generate();
              LocalDate birthDate = DateGenerator.generate();
              userService.create(new User(username, password, birthDate));
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
                    .minusMonths(countMonths)
                    .minusDays(countDays);
        }
    }
}
