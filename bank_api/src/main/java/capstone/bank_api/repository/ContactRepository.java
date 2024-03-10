//ContactRepository.java
package capstone.bank_api.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import capstone.bank_api.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
	
}