package linkplus.internshipchallenge.service;

import linkplus.internshipchallenge.model.*;


import java.util.*;

public interface IAccountService {
    void addTransaction(Transaction transaction);
    Account getAccountByID(int id);
    List<Account> listAllAccounts();
    void createBankAccount(Integer ownerName, Integer bankName);
}
