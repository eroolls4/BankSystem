package linkplus.internshipchallenge.service;

import linkplus.internshipchallenge.model.*;


import java.util.*;

public interface IBankService {
    double totalTransferAmount(int id);
    double totaltransactionFlatFee(int id);
    double totaltransactionPercentFee(int id);
    long percentageFeesCount(int id);
    long flatFeeesCount(int id);
    long transactionCount(int id);
    List<Bank> listAllBanks();
    Bank getBankById(int id);
    Bank findBankByName(String bankName);
    boolean bankHasUser(Integer bankID, String ownerName);
}
