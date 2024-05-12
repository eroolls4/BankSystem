package linkplus.internshipchallenge.service;

import linkplus.internshipchallenge.model.*;


import java.util.*;

public interface IAccountService {
    Account getAccountByID(long id);
    List<Account> listAllAccounts();
    void createBankAccount(Integer ownerName, Integer bankName);
}
