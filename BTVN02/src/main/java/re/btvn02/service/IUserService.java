package re.btvn02.service;

import re.btvn02.models.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User findById(String id);
    User addUser(User user);
    User updateUser(String id, User user);
    User deleteUser(String id);
}
