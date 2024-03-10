//CustomerRepository.java
package capstone.bank_api.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import capstone.bank_api.model.Customer;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findByEmail(String email);
    
}