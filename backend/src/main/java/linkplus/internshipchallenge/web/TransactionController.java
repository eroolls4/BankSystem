package linkplus.internshipchallenge.web;


import linkplus.internshipchallenge.model.*;
import linkplus.internshipchallenge.model.enums.*;
import linkplus.internshipchallenge.service.impl.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import java.util.*;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final UserService userService;
    private final TransactionService transactionService;
    private final BankService bankService;

    private final AccountService accountService;

    public TransactionController(UserService userService, TransactionService transactionService, BankService bankService, AccountService accountService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.bankService = bankService;
        this.accountService = accountService;
    }

    @GetMapping("/add")
    public String getForm(Model model) {
        List<Account> originatingAccounts = accountService.listAllAccounts();
        List<Account> resultingAccounts = accountService.listAllAccounts();
        model.addAttribute("originatingAccounts", originatingAccounts);
        model.addAttribute("resultingAccounts", resultingAccounts);
        model.addAttribute("feeTypes", TRANSACTION_TYPE.values());
        model.addAttribute("banks", bankService.listAllBanks());
        return "makeTransaction";
    }

    @PostMapping()
    public String create(
            @RequestParam Integer originatingID,
            @RequestParam Integer resultingID,
            @RequestParam Double amount,
            @RequestParam String feeType,
            @RequestParam Integer originatingBankID,
            @RequestParam Integer resultingBankID,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        try {
            this.transactionService.create(originatingID, resultingID, amount, feeType, originatingBankID,resultingBankID);
            redirectAttributes.addFlashAttribute("successMessage", "Successfully made a transaction");
            return "redirect:/main";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            List<Account> originatingAccounts = accountService.listAllAccounts();
            List<Account> resultingAccounts = accountService.listAllAccounts();
            model.addAttribute("originatingAccounts", originatingAccounts);
            model.addAttribute("resultingAccounts", resultingAccounts);
            model.addAttribute("feeTypes", TRANSACTION_TYPE.values());
            model.addAttribute("banks", bankService.listAllBanks());
            return "makeTransaction";
        }
    }
}
