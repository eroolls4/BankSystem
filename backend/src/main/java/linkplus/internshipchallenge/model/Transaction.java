package linkplus.internshipchallenge.model;

import jakarta.persistence.*;
import lombok.*;
import linkplus.internshipchallenge.model.enums.TRANSACTION_TYPE;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private TRANSACTION_TYPE transactionType;

    private int originatingAcc;
    private int resultingAcc;
    private double amount;

    public Transaction(int originatingAcc, int resultingAcc, double amount, TRANSACTION_TYPE transactionType) {
        this.originatingAcc = originatingAcc;
        this.resultingAcc = resultingAcc;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public Transaction(int originatingAcc, double amount, TRANSACTION_TYPE transactionType) {
        this.originatingAcc = originatingAcc;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public Transaction() {}
}
