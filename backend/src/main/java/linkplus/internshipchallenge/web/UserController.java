package linkplus.internshipchallenge.web;


import linkplus.internshipchallenge.model.*;
import linkplus.internshipchallenge.model.enums.*;
import linkplus.internshipchallenge.service.impl.*;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/main")
public class UserController {
    private final UserService userService;
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final BankService bankService;


    public UserController(UserService userService, TransactionService transactionService,
                          AccountService accountService, BankService bankService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.bankService = bankService;
    }


    @GetMapping
    public String getMainPage(Model model) {
        List<Bank> allBanks = bankService.listAllBanks();
        List<User> allUsers = userService.listAllUsers();
        List<Transaction> allTransactions = transactionService.listAllTransactions();
        List<Account> allAccounts = accountService.listAllAccounts();
        model.addAttribute("banks", allBanks);
        model.addAttribute("users", allUsers);
        model.addAttribute("transactions", allTransactions);
        model.addAttribute("accounts", allAccounts);
        return "index";
    }


    @GetMapping("/add")
    public String getAddForm(){
        return "addUserAccount";
    }


    @PostMapping()
    public String getAddForm(@RequestParam String username){
        this.userService.createUser(username);
        return "redirect:/main";
    }
}
