package generator.jdbc;

import core.dto.UserDto;
import generator.service.GenerateUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class UserJdbcRepository {
    private static final Logger log = LoggerFactory.getLogger(UserJdbcRepository.class);
    private static final String SAVE_USER_QUERY = "INSERT INTO USERS (id, username, password, age, birth_date, created_at, balance) " +
            "VALUES (?,?,?,?,?,?,?)";
    private static final String SAVE_PURCHASE_HISTORY_QUERY = "INSERT INTO PURCHASE_HISTORY (user_id) " +
            "VALUES (?)";
    public boolean save(UserDto userDto) {
        int usersRowsUpdated = 0, purchaseHistoryRowsUpdated = 0;
        try (Connection con = ConnectionManager.get()) {
          PreparedStatement statement = con.prepareStatement(SAVE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
          statement.setLong(1, userDto.getId());
          statement.setString(2, userDto.getUsername());
          statement.setString(3, userDto.getPassword());
          statement.setInt(4, userDto.getAge());
          statement.setObject(5, userDto.getBirthDate());
          statement.setObject(6, userDto.getCreatedAt());
          statement.setDouble(7, userDto.getBalance());
          usersRowsUpdated = statement.executeUpdate();
          ResultSet keys = statement.getGeneratedKeys();
          if (keys.next()) {
              PreparedStatement preparedStatement = con.prepareStatement(SAVE_PURCHASE_HISTORY_QUERY);
              preparedStatement.setLong(1, keys.getLong(1));
              purchaseHistoryRowsUpdated = preparedStatement.executeUpdate();
          }
        } catch (SQLException e) {
            log.error("exception was thrown ", e);
        }
        return usersRowsUpdated > 0 && purchaseHistoryRowsUpdated > 0;
    }
}