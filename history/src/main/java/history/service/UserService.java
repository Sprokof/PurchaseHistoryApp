package history.service;

import history.entities.User;
import history.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return this.userRepository.create(user);
    }

    public User getById(long id) {
        return this.userRepository.getById(id);
    }

    public void update(User user) {
        this.userRepository.update(user);
    }

    public User getWithPurchaseHistoryAndOperations(long id) {
        return this.userRepository.getWithPurchaseHistoryAndOperations(id);
    }

}


