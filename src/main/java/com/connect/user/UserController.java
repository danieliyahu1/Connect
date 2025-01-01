package com.connect.user;

import com.connect.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAll()
    {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id)
    {
        try{
            return userService.getUserById(id);
        }
        catch(Exception e)
        {
            throw new UserNotFoundException("User not found, can not get user");
        }
    }

    @PostMapping("users")
    public User addUser(@Valid @RequestBody User user)
    {
        return userService.addUser(user);
    }

    @PutMapping("users/{id}")
    public User editUser(@Valid @RequestBody User user, @PathVariable int id) throws InvocationTargetException, IllegalAccessException {
        try{
            return userService.editUser(user, id);
        }
        catch(Exception e)
        {
            throw new UserNotFoundException("User not found, can not edit user");
        }
    }

    @DeleteMapping("users/{id}")
    public User deleteUser(@PathVariable int id)
    {
        try{
            return userService.deleteUser(id);
        }
        catch(Exception e)
        {
            throw new UserNotFoundException("User not found, can not delete user");
        }
    }
}
