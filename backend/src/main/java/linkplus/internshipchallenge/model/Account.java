package linkplus.internshipchallenge.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    private double balance;

    @OneToMany(mappedBy = "originatingAcc", cascade = CascadeType.ALL)
    private List<Transaction> transactionList;
    @ManyToOne
    private Bank accountInParticularBank;

    public Account(User owner, Bank accountInParticularBank) {
        this.owner = owner;
        this.accountInParticularBank = accountInParticularBank;
        this.balance = 0.0;
        this.transactionList = new ArrayList<>();
    }

    public Account() { }
}
