
package linkplus.internshipchallenge.model;


import linkplus.internshipchallenge.model.enums.*;
import lombok.*;

@Data
public class Transaction {

    private static int ID_TRACKER = 1;

    private int id;


    private TRANSACTION_TYPE transactionType;
    private int originatingAcc;
    private int resultingAcc;
    private double amount;


    public Transaction(
            int originatingAcc,
            int resultingAcc,
            double amount,
            TRANSACTION_TYPE transactionType) {

        this.id = ID_TRACKER++;

        this.originatingAcc = originatingAcc;
        this.resultingAcc= resultingAcc;
        this.amount = amount;
        this.transactionType=transactionType;
    }


    public Transaction(
            int originatingAcc,
            double amount,
            TRANSACTION_TYPE transactionType) {

        this.id = ID_TRACKER++;

        this.originatingAcc = originatingAcc;
        this.amount = amount;
        this.transactionType=transactionType;
    }
}
