package linkplus.internshipchallenge.service.impl;

import linkplus.internshipchallenge.config.*;
import linkplus.internshipchallenge.model.*;
import linkplus.internshipchallenge.model.enums.*;
import linkplus.internshipchallenge.service.*;
import org.springframework.stereotype.*;

import java.util.*;


@Service
public class BankService implements IBankService {


    @Override
    public double totalTransferAmount(int id) {
        return getBankById(id).getAccounts().stream()
                .flatMap(a -> a.getTransactionList().stream())
                .filter(t -> t.getTransactionType().toString().contains("TRANSFER"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double totaltransactionFlatFee(int id) {
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
    public double totaltransactionPercentFee(int id) {
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
    public long percentageFeesCount(int id) {
        return listAllBanks()
                .stream()
                .filter(b -> b.getId() == id)
                .flatMap(b -> b.getAccounts().stream())
                .flatMap(a -> a.getTransactionList().stream())
                .filter(t -> t.getTransactionType().toString().equals(TRANSACTION_TYPE.TRANSFER_PERCENTAGE_FEE.toString()))
                .count();
    }

    @Override
    public long flatFeeesCount(int id) {
        return listAllBanks()
                .stream()
                .filter(b -> b.getId() == id)
                .flatMap(b -> b.getAccounts().stream())
                .flatMap(a -> a.getTransactionList().stream())
                .filter(t -> t.getTransactionType().toString().equals(TRANSACTION_TYPE.TRANSFER_FLAT_FEE.toString()))
                .count();
    }

    @Override
    public long transactionCount(int id) {
        return getBankById(id)
                .getAccounts()
                .stream().mapToLong(a -> a.getTransactionList().size())
                .sum();
    }

    @Override
    public List<Bank> listAllBanks() {
        return DataHolder.bankList;
    }

    @Override
    public Bank getBankById(int id) {
        return listAllBanks().stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public Bank findBankByName(String bankName) {
        return listAllBanks().stream()
                .filter(b -> b.getBankName().equals(bankName))
                .findFirst()
                .get();
    }

    @Override
    public boolean bankHasUser(Integer bankID, String ownerName) {
        Bank bank = getBankById(bankID);

        return bank.getAccounts().stream()
                   .anyMatch(a -> a.getOwner().getUsername().equals(ownerName));
    }
}
