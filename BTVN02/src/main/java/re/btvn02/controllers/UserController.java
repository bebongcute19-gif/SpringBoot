package re.btvn02.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.btvn02.models.User;
import re.btvn02.service.impl.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(@RequestParam(required = false) String search) {
        List<User> users = userService.findAll();
        if (search != null) {
            users = users.stream().filter(user -> user.getUsername().contains(search)).toList();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        User user = userService.findById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.addUser(user));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user) {
        User updated = userService.updateUser(id, user);
        if(updated == null){ return ResponseEntity.notFound().build();
        }
         return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        User deleted = userService.deleteUser(id);
        if(deleted == null){ return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

