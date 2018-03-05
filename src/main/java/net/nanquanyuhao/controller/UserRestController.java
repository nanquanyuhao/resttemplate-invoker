package net.nanquanyuhao.controller;

import net.nanquanyuhao.domain.User;
import net.nanquanyuhao.exception.RestException;
import net.nanquanyuhao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mertcaliskan
 * on 18/09/14.
 */
@RestController
@RequestMapping("/rest")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method=RequestMethod.POST)
    public void save(@RequestBody User user) {

        // 额外添加已存在的判断
        User old  = userRepository.find(user.getId());
        if (old != null) {
            throw new RestException(1, "The user has already existed!",  "User with id: " + user.getId() + " has already existed in the system");
        }

        userRepository.save(user);
    }

    @RequestMapping(value = "/users", method=RequestMethod.GET)
    public List<User> list() {
        List<User> users = userRepository.findAll();

        // 添加额外判断，均不存在则抛异常
        if (users == null || users.size() == 0) {
            throw new RestException(1, "Users not found!", "There are not any users in the system");
        }

        return userRepository.findAll();
    }

    @RequestMapping(value="/users/{id}", method= RequestMethod.GET)
    public User get(@PathVariable("id") int id) {
        User user = userRepository.find(id);

        // 额外添加是否存在的判断，不存在抛异常统一处理
        if (user == null) {
            throw new RestException(1, "User not found!", "User with id: " + id + " not found in the system");
        }
        return user;
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
    public void update(@PathVariable("id") int id, @RequestBody User user) {

        // 额外添加已存在的判断
        User old  = userRepository.find(id);
        if (old == null) {
            throw new RestException(1, "User not found!", "User with id: " + id + " not found in the system");
        }

        userRepository.update(id, user);
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {

        // 额外添加已存在的判断
        User old  = userRepository.find(id);
        if (old == null) {
            throw new RestException(1, "User not found!", "User with id: " + id + " not found in the system");
        }
        userRepository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
}
