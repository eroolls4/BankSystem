package linkplus.internshipchallenge.model;

import lombok.*;

import java.util.*;

@Data
public class User {

    private static int ID_TRACKER = 1;
    private int id;
    List<Account> associatedAccounts;
    private String username;

    public User() { }

    public User(String username) {
        this.username = username;
        this.id = ID_TRACKER++;
        associatedAccounts=new ArrayList<>();
    }
}
