package linkplus.internshipchallenge.config;
//
//
//import jakarta.annotation.*;
//import linkplus.internshipchallenge.model.*;
//import lombok.*;
//import org.springframework.stereotype.*;
//
//import java.util.*;
//
//@Component
//@Getter
//public class DataHolder {
//    public static List<Account> accountList;
//    public static List<Bank> bankList;
//    public static List<Transaction> transactionList;
//    public static List<User> users;
//
//
//
//
//    @PostConstruct
//    public void initLists() {
//        accountList=new ArrayList<>();
//        bankList=new ArrayList<>();
//        transactionList=new ArrayList<>();
//        users=new ArrayList<>();
//
//
//        Bank stopanska=new Bank("STOPANSKA BANKA");
//        Bank NLB=new Bank("NLB");
//        Bank KOMERCIALNA=new Bank("KOMERCIALNA");
//        Bank OTPBANKA=new Bank("OTP-BANKA");
//
//
//        User user1 = new User("eroll");
//        User user2 = new User("test");
//        users.add(user1);
//        users.add(user2);
//
//        Account accountEroll=new Account(user1,stopanska);
//        Account accountTest=new Account(user2,stopanska);
//
//
//        accountEroll.setBalance(150.00);
//        accountTest.setBalance(50.00);
//
//
//        user1.getAssociatedAccounts().add(accountEroll);
//        user2.getAssociatedAccounts().add(accountTest);
//        accountList.add(accountEroll);
//        accountList.add(accountTest);
//
//
//
//        stopanska.addAccount(accountEroll);
//        stopanska.addAccount(accountTest);
//
//        bankList.add(stopanska);
//        bankList.add(NLB);
//        bankList.add(KOMERCIALNA);
//        bankList.add(OTPBANKA);
//
//
//    }
//}
