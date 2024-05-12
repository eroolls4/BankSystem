package linkplus.internshipchallenge.service.impl;

import linkplus.internshipchallenge.config.*;
import linkplus.internshipchallenge.model.*;
import linkplus.internshipchallenge.model.enums.*;
import linkplus.internshipchallenge.repository.*;
import linkplus.internshipchallenge.service.*;
import org.springframework.stereotype.*;

import java.util.*;


@Service
public class TransactionService implements ITransactionService {
    private static final double TRANSACTION_PERCENTAGE_FEE=0.05;
    private static final int TRANSACTION_FLAT_FEE=10;


    private final TransactionRepository transactionRepository;
    private final BankService bankService;

    public TransactionService(TransactionRepository transactionRepository, BankService bankService) {
        this.transactionRepository = transactionRepository;
        this.bankService = bankService;
    }

    @Override
    public List<Transaction> listAllTransactions() {
        return transactionRepository.findAll();
    }


    @Override
    public Transaction create(Integer originatingAccID, Integer resultingAccID, Double amount, String feeType, Integer originatingBankId, Integer resultingBankID) {
        Transaction transaction = null;
        Bank originatingBank = bankService.getBankById(originatingBankId);

        Account originatingAccount = originatingBank.getAccounts().stream()
                .filter(a -> a.getId() == originatingAccID)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Originating account does not belong to the specified bank"));

        if (TRANSACTION_TYPE.TRANSFER_PERCENTAGE_FEE.toString().equals(feeType) ||
                TRANSACTION_TYPE.TRANSFER_FLAT_FEE.toString().equals(feeType)) {

            TRANSACTION_TYPE ft= TRANSACTION_TYPE.TRANSFER_PERCENTAGE_FEE.toString().equals(feeType) ?
                                   TRANSACTION_TYPE.TRANSFER_PERCENTAGE_FEE : TRANSACTION_TYPE.TRANSFER_FLAT_FEE;

            double fee = (TRANSACTION_TYPE.TRANSFER_PERCENTAGE_FEE.toString().equals(feeType)) ?
                    amount * TRANSACTION_PERCENTAGE_FEE : TRANSACTION_FLAT_FEE;

            Bank resultingBank = bankService.getBankById(resultingBankID);

            Account resultingAccount = resultingBank.getAccounts().stream()
                    .filter(a -> a.getId() == resultingAccID)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Resulting account does not belong to the specified bank"));

            if (originatingBankId.equals(resultingBankID)) { //same bank transfer
                if (originatingAccount.getBalance() < amount) {
                    throw new IllegalArgumentException("Insufficient balance in the originating account");
                }

                originatingAccount.setBalance(originatingAccount.getBalance() - amount);
                resultingAccount.setBalance(resultingAccount.getBalance() + amount);


                transaction = new Transaction(originatingAccID, resultingAccID, amount + fee, ft);
            } else {  //different bank transfer
                if (originatingAccount.getBalance() < amount) {
                    throw new IllegalArgumentException("Insufficient balance in the originating account");
                }
                originatingAccount.setBalance(originatingAccount.getBalance() - amount);
                resultingAccount.setBalance(resultingAccount.getBalance() + amount);
                transaction = new Transaction(originatingAccID, resultingAccID, amount, ft);
            }
        } else if (TRANSACTION_TYPE.DEPOSIT.toString().equals(feeType)) { //DEPOSIT
            originatingAccount.setBalance(originatingAccount.getBalance() + amount);
            transaction = new Transaction(originatingAccID, amount, TRANSACTION_TYPE.DEPOSIT);
        } else if (TRANSACTION_TYPE.WITHDRAW.toString().equals(feeType)) {
            if (originatingAccount.getBalance() < amount) {
                throw new IllegalArgumentException("Insufficient balance in the originating account");
            }
            originatingAccount.setBalance(originatingAccount.getBalance() - amount);
            transaction = new Transaction(originatingAccID, amount, TRANSACTION_TYPE.WITHDRAW);
        } else {
            throw new IllegalArgumentException("Invalid transaction type");
        }

        originatingAccount.getTransactionList().add(transaction);
        return transactionRepository.save(transaction);
    }


}
