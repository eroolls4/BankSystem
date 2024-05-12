package linkplus.internshipchallenge.repository;


import linkplus.internshipchallenge.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface BankRepository  extends JpaRepository<Bank,Long> {
    Bank findBankByBankName(String bankName);
}
