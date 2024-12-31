package com.connect.user;

import org.apache.commons.beanutils.BeanUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import com.connect.exception.UserNotFoundException;


@Service
public class UserService {
    UsersWrapper usersWrapper;

    public UserService() {
        usersWrapper = new UsersWrapper();
    }

    @PostConstruct
    private void init()
    {
        usersWrapper.init();
    }

    public List<User> getAll()
    {
        return this.getUsers();
    }

    public User getUserById(int id)
    {
        Optional<User> optionalUser = this.getUsers().stream().filter(user -> user.getId() == id).findFirst();
        if(optionalUser.isEmpty())
        {
            this.userNotFoundException();
        }
        return optionalUser.get();
    }

    public User addUser(User user)
    {
        List<User> users = this.getUsers();
        users.add(user);
        this.setUsersWrapper(users);
        return user;
    }

    public User editUser(User user, int id) throws InvocationTargetException, IllegalAccessException {
        if(!this.checkUserExisit(id))
        {
            this.userNotFoundException();
        }

        User prevUser = this.getUserById(id);

        ///****ask Ori about this line****
        BeanUtils.copyProperties(prevUser, user);

        return prevUser;
    }

    public User deleteUser(int id)
    {
        if(!this.checkUserExisit(id))
        {
            this.userNotFoundException();
        }
        List<User> users = this.getUsers();
        User userToDelete = this.getUserById(id);
        users = users.stream().filter(user -> user.getId() != id).toList();
        this.setUsersWrapper(users);

        return userToDelete;
    }

    private List<User> getUsers()
    {
        return usersWrapper.getUsers();
    }

    private void setUsersWrapper(List<User> users)
    {
        this.usersWrapper.setUsers(users);
    }

    private boolean checkUserExisit(int id)
    {
        return !this.getUsers().stream().filter(user -> user.getId() == id).findFirst().isEmpty();
    }

    private UserNotFoundException userNotFoundException()
    {
        throw new UserNotFoundException("User not found, can not get user");
    }
}