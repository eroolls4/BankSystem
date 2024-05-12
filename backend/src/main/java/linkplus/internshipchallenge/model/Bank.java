package linkplus.internshipchallenge.model;


import lombok.*;

import java.util.*;


/**
 * The bank charges a fee for each transaction, either a flat fee (e.g. $10) or percent fee (e.g. 5%)
 * that need to be defined. The bank system should support both transaction types.
 * Bank:
 * - bank name
 * - list of accounts
 * - total transaction fee amount
 * - total transfer amount
 * - transaction flat fee amount
 * <p>
 * - transaction percent fee value
 */
@Data

public class Bank {
    private static int ID_TRACKER = 1;
    private int id;
    private final String bankName;
    private List<Account> accounts;
    public Bank(String bankName) {
        this.id = ID_TRACKER++;
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
    }
    public void addAccount(Account account) {
        accounts.add(account);
    }

}
