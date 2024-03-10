// AccountTransactionsRepository.java
package capstone.bank_api.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import capstone.bank_api.model.AccountTransactions;

@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, String> {
    
    List<AccountTransactions> findByCustomer_IdOrderByTransactionDtDesc(int customerId);
    
}
