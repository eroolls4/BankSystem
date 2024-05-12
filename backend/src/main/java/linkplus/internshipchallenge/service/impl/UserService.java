package linkplus.internshipchallenge.service.impl;

import linkplus.internshipchallenge.config.*;
import linkplus.internshipchallenge.model.*;
import linkplus.internshipchallenge.service.*;
import org.springframework.stereotype.*;

import java.util.*;


@Service
public class UserService implements IUserService {
    @Override
    public void addAccount(Account account) {

    }

    @Override
    public List<Account> getAllAccountPerUser() {
        return null;
    }

    @Override
    public List<User> listAllUsers() {
        return DataHolder.users;
    }

    @Override
    public User findByUsername(String username) {
        return listAllUsers().stream().filter(u -> u.getUsername().equals(username))
                .findFirst().get();
    }

    @Override
    public void createUser(String username) {
        User user = new User(username);
        DataHolder.users.add(user);
    }

    @Override
    public User findByID(Integer userID) {
        return listAllUsers().stream().filter(u -> u.getId() == userID).findFirst().get();
    }
}
