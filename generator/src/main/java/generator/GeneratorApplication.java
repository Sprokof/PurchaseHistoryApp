package generator;


import generator.jdbc.UserJdbcRepository;
import generator.service.GenerateUserService;

public class GeneratorApplication {
    public static void main(String[] args) throws Exception {
        UserJdbcRepository userJdbcRepository = new UserJdbcRepository();
        GenerateUserService userService = new GenerateUserService(userJdbcRepository);
        userService.run();
    }
}