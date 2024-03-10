// CardsRepository.java 
package capstone.bank_api.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import capstone.bank_api.model.Cards;
import capstone.bank_api.model.Customer;

@Repository
public interface CardsRepository extends CrudRepository<Cards, Integer> {
    List<Cards> findByCustomer(Customer customer);
}
