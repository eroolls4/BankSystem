package linkplus.internshipchallenge.service;

import linkplus.internshipchallenge.model.*;


import java.util.*;

public interface ITransactionService {
    List<Transaction> listAllTransactions();

    Transaction create(Integer originatingID, Integer resultingID, Double amount, String feeType, Integer originatingBankId, Integer resultingBankID);
}
