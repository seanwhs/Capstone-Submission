// LoanRepository.java
package capstone.bank_api.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import capstone.bank_api.model.Loans;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Integer> {
    List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);
}
