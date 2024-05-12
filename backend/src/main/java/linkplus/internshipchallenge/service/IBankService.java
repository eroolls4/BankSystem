package linkplus.internshipchallenge.service;

import linkplus.internshipchallenge.model.*;


import java.util.*;

public interface IBankService {
    double totalTransferAmount(long id);
    double totaltransactionFlatFee(long id);
    double totaltransactionPercentFee(long id);
    long percentageFeesCount(long id);
    long flatFeeesCount(long id);
    long transactionCount(long id);
    List<Bank> listAllBanks();
    Bank getBankById(long id);
    Bank findBankByName(String bankName);

    boolean bankHasUser(long bankID, String ownerName);

    Bank create(String bankName);
}
