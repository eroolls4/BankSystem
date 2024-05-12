package linkplus.internshipchallenge.service.impl;

import linkplus.internshipchallenge.config.*;
import linkplus.internshipchallenge.model.*;
import linkplus.internshipchallenge.model.enums.*;
import linkplus.internshipchallenge.repository.*;
import linkplus.internshipchallenge.service.*;
import org.springframework.stereotype.*;

import java.util.*;


@Service
public class BankService implements IBankService {



    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public double totalTransferAmount(long id) {
        return getBankById(id).getAccounts().stream()
                .flatMap(a -> a.getTransactionList().stream())
                .filter(t -> t.getTransactionType().toString().contains("TRANSFER"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double totaltransactionFlatFee(long id) {
        return listAllBanks()
                .stream()
                .filter(b -> b.getId() == id)
                .flatMap(b -> b.getAccounts().stream())
                .flatMap(a -> a.getTransactionList().stream())
                .filter(t -> t.getTransactionType().toString().equals(TRANSACTION_TYPE.TRANSFER_FLAT_FEE.toString()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double totaltransactionPercentFee(long id) {
        return listAllBanks()
                .stream()
                .filter(b -> b.getId() == id)
                .flatMap(b -> b.getAccounts().stream())
                .flatMap(a -> a.getTransactionList().stream())
                .filter(t -> t.getTransactionType().toString().equals(TRANSACTION_TYPE.TRANSFER_PERCENTAGE_FEE.toString()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public long percentageFeesCount(long id) {
        return listAllBanks()
                .stream()
                .filter(b -> b.getId() == id)
                .flatMap(b -> b.getAccounts().stream())
                .flatMap(a -> a.getTransactionList().stream())
                .filter(t -> t.getTransactionType().toString().equals(TRANSACTION_TYPE.TRANSFER_PERCENTAGE_FEE.toString()))
                .count();
    }

    @Override
    public long flatFeeesCount(long id) {
        return listAllBanks()
                .stream()
                .filter(b -> b.getId() == id)
                .flatMap(b -> b.getAccounts().stream())
                .flatMap(a -> a.getTransactionList().stream())
                .filter(t -> t.getTransactionType().toString().equals(TRANSACTION_TYPE.TRANSFER_FLAT_FEE.toString()))
                .count();
    }

    @Override
    public long transactionCount(long id) {
        return getBankById(id)
                .getAccounts()
                .stream().mapToLong(a -> a.getTransactionList().size())
                .sum();
    }

    @Override
    public List<Bank> listAllBanks() {
       return bankRepository.findAll();
    }

    @Override
    public Bank getBankById(long id) {
          return bankRepository.findById(id).get();
    }

    @Override
    public Bank findBankByName(String bankName) {
         return bankRepository.findBankByBankName(bankName);
    }

    @Override
    public boolean bankHasUser(long bankID, String ownerName) {
        Bank bank = getBankById(bankID);

        return bank.getAccounts().stream()
                   .anyMatch(a -> a.getOwner().getUsername().equals(ownerName));
    }

    @Override
    public Bank create(String bankName) {
        return bankRepository.save(new Bank(bankName));
    }
}
