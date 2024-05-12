package linkplus.internshipchallenge.web;


import linkplus.internshipchallenge.model.*;
import linkplus.internshipchallenge.model.enums.*;
import linkplus.internshipchallenge.service.impl.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final UserService userService;
    private final BankService bankService;

    public AccountController(AccountService accountService, UserService userService, BankService bankService) {
        this.accountService = accountService;
        this.userService = userService;
        this.bankService = bankService;
    }

    @GetMapping("/add")
    public String createBankAccount(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        model.addAttribute("banks", bankService.listAllBanks());
        return "addBankAccount";
    }


    @PostMapping("/add")
    public String createBankAccount(@RequestParam Integer ownerID,
                                    @RequestParam Integer bankID,
                                    Model model) {
        try {
            this.accountService.createBankAccount(ownerID, bankID);
            return "redirect:/main";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("users", userService.listAllUsers());
            model.addAttribute("banks", bankService.listAllBanks());
            return "addBankAccount";
        }
    }
}
