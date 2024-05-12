package linkplus.internshipchallenge.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "banks")
@Data
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String bankName;
    @OneToMany(mappedBy = "accountInParticularBank", cascade = CascadeType.ALL)
    private List<Account> accounts;

    public Bank(String bankName) {
        this.bankName=bankName;
        this.accounts = new ArrayList<>();
    }

    public Bank() { }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
