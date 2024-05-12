package linkplus.internshipchallenge.model;


import lombok.*;
import org.hibernate.query.sql.internal.*;

import java.util.*;

@Data

public class Account {
    private static int ID_TRACKER = 1;
    private int id;
    private User owner;
    private double balance;
    private List<Transaction> transactionList;
    private Bank accountInParticularBank;

    public Account(User owner,Bank accountInParticularBank) {
        this.owner = owner;
        this.accountInParticularBank=accountInParticularBank;
        balance = 0.0;
        this.id = ID_TRACKER++;
        transactionList = new ArrayList<>();
    }
}
