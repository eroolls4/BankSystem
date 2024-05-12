package linkplus.internshipchallenge.service;

import linkplus.internshipchallenge.model.*;


import java.util.*;

public interface IUserService {

    void addAccount(Account account);

    List<Account> getAllAccountPerUser();

    List<User> listAllUsers();

    User findByUsername(String username);

    void createUser(String username);

    User findByID(Integer ownerName);
}
