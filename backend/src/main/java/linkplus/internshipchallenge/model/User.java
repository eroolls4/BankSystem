package linkplus.internshipchallenge.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "bank_users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Account> associatedAccounts;
    private String username;

    public User() { }

    public User(String username) {
        this.username = username;
        associatedAccounts = new ArrayList<>();
    }
}
