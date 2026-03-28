package re.btvn02.repository;

import org.springframework.stereotype.Repository;
import re.btvn02.enums.Role;
import re.btvn02.models.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User("1", "tung", "tung@gmail.com", Role.Manager));
        users.add(new User("2", "nam", "nam@gmail.com", Role.Staff));
        users.add(new User("3", "linh", "linh@gmail.com", Role.Staff));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(String id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User save(User user) {
        if (findById(user.getId()) != null) {
            return null;
        }
        users.add(user);
        return user;
    }

    public User update(String id, User newUser) {
        User oldUser = findById(id);
        if (oldUser == null) return null;

        oldUser.setUsername(newUser.getUsername());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setRole(newUser.getRole());

        return oldUser;
    }

    public User deleteById(String id) {
        User oldUser = findById(id);
        if (oldUser != null) {
            users.remove(oldUser);
        }
        return oldUser;
    }
}
