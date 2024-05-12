package linkplus.internshipchallenge.service.impl;

import linkplus.internshipchallenge.config.*;
import linkplus.internshipchallenge.model.*;
import linkplus.internshipchallenge.model.enums.*;
import linkplus.internshipchallenge.service.*;

import org.springframework.stereotype.*;


import java.util.*;


@Service
public class AccountService implements IAccountService {
    private final UserService userService;
    private final BankService bankService;

    public AccountService(UserService userService, BankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
    }
    @Override
    public void addTransaction(Transaction transaction) {

    }
    @Override
    public Account getAccountByID(int id) {
        return listAllAccounts().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .get();
    }
    @Override
    public List<Account> listAllAccounts() {
        return DataHolder.accountList;
    }
    @Override
    public void createBankAccount(Integer ownerID, Integer bankID) {
        User owner = userService.findByID(ownerID);
        Bank bank = bankService.getBankById(bankID);
        if (bankService.bankHasUser(bankID, owner.getUsername())) {
            throw new IllegalArgumentException("THE SELECTED BANK HAS ALREADY THIS USER");
        }
        Account account = new Account(owner,bank);
        bank.addAccount(account);
        owner.getAssociatedAccounts().add(account);
        DataHolder.accountList.add(account);
    }
}
