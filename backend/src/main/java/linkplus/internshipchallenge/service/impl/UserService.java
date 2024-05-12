package linkplus.internshipchallenge.service.impl;

import linkplus.internshipchallenge.config.*;
import linkplus.internshipchallenge.model.*;
import linkplus.internshipchallenge.repository.*;
import linkplus.internshipchallenge.service.*;
import org.springframework.stereotype.*;

import java.util.*;


@Service
public class UserService implements IUserService {


   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User createUser(String username) {
        User user = new User(username);
       return userRepository.save(user);
    }

    @Override
    public User findByID(long userID) {
        return userRepository.findById(userID).get();
    }
}
