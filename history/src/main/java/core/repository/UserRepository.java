package core.repository;

import core.entities.User;

public interface UserRepository {
    User create(User user);
}
