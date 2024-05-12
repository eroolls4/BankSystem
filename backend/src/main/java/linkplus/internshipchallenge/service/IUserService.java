package linkplus.internshipchallenge.service;

import linkplus.internshipchallenge.model.*;


import java.util.*;

public interface IUserService {


    List<User> listAllUsers();

    User findByUsername(String username);

    User createUser(String username);

    User findByID(long userID);
}
