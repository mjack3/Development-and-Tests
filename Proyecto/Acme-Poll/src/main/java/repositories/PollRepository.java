
package repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {

	@Query("select p from Poll p where timeActive >= ?1")
	List<Poll> findPollActivated(Date today);

}
