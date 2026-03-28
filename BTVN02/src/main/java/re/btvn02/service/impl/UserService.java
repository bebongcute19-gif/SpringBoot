package re.btvn02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.btvn02.models.User;
import re.btvn02.repository.UserRepository;
import re.btvn02.service.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User findById(String id) {
        return userRepository.findById(id);
    }
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User updateUser(String id, User user) {
        return userRepository.update(id, user);
    }
    @Override
    public User deleteUser(String id) {
        return userRepository.deleteById(id);
    }
}
