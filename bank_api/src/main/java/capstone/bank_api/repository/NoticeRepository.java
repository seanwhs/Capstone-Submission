//NoticeRepository.java
package capstone.bank_api.repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import capstone.bank_api.model.Notice;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Integer> {
	//filter out expired queries
	@Query(value = "from Notice n where CURDATE() BETWEEN noticBegDt AND noticEndDt")
	List<Notice> findAllActiveNotices();

    List<Notice> findByNoticeSummaryContaining(String noticeSummary); 
}