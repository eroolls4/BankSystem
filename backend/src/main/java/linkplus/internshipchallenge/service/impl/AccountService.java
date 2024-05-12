package linkplus.internshipchallenge.service.impl;


import linkplus.internshipchallenge.model.*;

import linkplus.internshipchallenge.repository.*;
import linkplus.internshipchallenge.service.*;

import org.springframework.stereotype.*;


import java.util.*;


@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    private final UserService userService;
    private final BankService bankService;



    public AccountService(AccountRepository accountRepository, UserService userService, BankService bankService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.bankService = bankService;
    }

    @Override
    public Account getAccountByID(long id) {
          return accountRepository.findById(id).get();
    }
    @Override
    public List<Account> listAllAccounts() {
        return accountRepository.findAll();
    }
    @Override
    public void createBankAccount(Integer ownerID, Integer bankID) {
        User owner = userService.findByID(ownerID);
        Bank bank = bankService.getBankById(bankID);
        if (bankService.bankHasUser(bankID, owner.getUsername())) {
            throw new IllegalArgumentException("THE SELECTED BANK HAS ALREADY THIS USER");
        }
        Account account = new Account(owner,bank);
        accountRepository.save(account);
        bank.addAccount(account);
        owner.getAssociatedAccounts().add(account);
    }
}
