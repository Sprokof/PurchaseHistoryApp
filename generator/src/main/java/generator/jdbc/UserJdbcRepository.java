package generator.jdbc;

import core.dto.UserDto;
import generator.service.GenerateUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserJdbcRepository {
    private static final Logger log = LoggerFactory.getLogger(UserJdbcRepository.class);
    private static final String SAVE_USER_QUERY = "INSERT INTO USERS (username, password, age, birth_date, createdAt, balance) " +
            "VALUES (?,?,?,?,?,?)";
    private static final String SAVE_PURCHASE_HISTORY_QUERY = "INSERT INTO PURCHASE_HISTORY (userId) " +
            "VALUES (?)";
    public boolean save(UserDto userDto) {
        int usersRowsUpdated = 0, purchaseHistoryRowsUpdated = 0;
        try(Connection con = ConnectionManager.get()) {
          PreparedStatement statement = con.prepareStatement(SAVE_USER_QUERY);
          statement.setString(1, userDto.getUsername());
          statement.setString(2, userDto.getPassword());
          statement.setInt(3, userDto.getAge());
          statement.setObject(4, userDto.getBirthDate());
          statement.setObject(5, userDto.getCreatedAt());
          statement.setDouble(6, userDto.getBalance());
          usersRowsUpdated = statement.executeUpdate();
          long userId = statement.getGeneratedKeys().getLong(1);
          statement = con.prepareStatement(SAVE_PURCHASE_HISTORY_QUERY);
          statement.setLong(1, userId);
          purchaseHistoryRowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            log.error("exception was thrown ", e);
        }
        return usersRowsUpdated > 0 && purchaseHistoryRowsUpdated > 0;
    }
}
;