// AccountsRepository.java
package capstone.bank_api.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import capstone.bank_api.model.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, String> {

    Accounts findByCustomerId(int customerId);

}