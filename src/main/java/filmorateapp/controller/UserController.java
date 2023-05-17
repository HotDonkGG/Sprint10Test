package filmorateapp.controller;

import filmorateapp.service.UserService;
import filmorateapp.storage.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import filmorateapp.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import filmorateapp.service.ValidationService;

import java.util.ArrayList;
import java.util.List;

// Надо ловить конкретную ошибку
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final List<User> users = new ArrayList<>();
    private int nextId = 0;
    @Autowired
    private ValidationService validationService; // загуглить инжекты бина виды

    @PostMapping
    public User addUsers(@RequestBody User user) {
        try {
            validationService.validate(user);
            user.setId(nextId++);
            users.add(user);
            log.info("Добавлен новый пользователь" + user.getId());
        } catch (Exception e) {
            log.error("Ошибка добавления пользователя" + e.getMessage());
        }
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == id) {
                    validationService.validate(user);
                    users.set(i, user);
                    log.info("Обновление пользователя с айди" + id);
                    return user;
                }
            }
        } catch (Exception e) {
            log.error("Ошибка обновления пользователя " + e.getMessage());
        }
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        try {
            if (!users.isEmpty()) {
                log.info("Получите список пользователей " + users);
                return users;
            }
        } catch (Exception e) {
            log.error("Мапа пуста " + e.getMessage());
        }
        return users;
    }

    @PutMapping("/{id}/friends/{friendId}")
    public ResponseEntity<String> addFriend(@PathVariable int id, @PathVariable int friendId) {
        return null;
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public ResponseEntity<String> deleteFriend(@PathVariable int id, @PathVariable int friendId) {
        return null;
    }

    @GetMapping("/{id}/friends")
    public ResponseEntity<List<User>> getFriends(@PathVariable int id){
        return null;
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public ResponseEntity<List<User>> getMutualFriend(@PathVariable int id, @PathVariable int friendId){
        return null;
    }
}