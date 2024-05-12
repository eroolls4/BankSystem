package linkplus.internshipchallenge.web;


import linkplus.internshipchallenge.model.*;
import linkplus.internshipchallenge.service.impl.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/banks")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("{id}")
    public String getBankDetails(@PathVariable("id") int id,
                                 Model model) {

        Bank bank = bankService.getBankById(id);
        if (bank != null) {
            model.addAttribute("bank", bank);
            model.addAttribute("totalTransactionAmount", bankService.totalTransferAmount(id));
            model.addAttribute("transactionCount", bankService.transactionCount(id));
            model.addAttribute("flatFeesCount", bankService.flatFeeesCount(id));
            model.addAttribute("percentageFeesCount", bankService.percentageFeesCount(id));
            model.addAttribute("flatFeeAmount", bankService.totaltransactionFlatFee(id));
            model.addAttribute("percentageFeeAmount", bankService.totaltransactionPercentFee(id));
            return "bankDetails";
        } else {
            return "bank-not-found";
        }
    }


    @GetMapping("/add")
    public String addBank(){
        return "addBank";
    }

    @PostMapping("/add")
    public String addBank(@RequestParam String bankName){
        this.bankService.create(bankName);
        return "redirect:/main";
    }


}
