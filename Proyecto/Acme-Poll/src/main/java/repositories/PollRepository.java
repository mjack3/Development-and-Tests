
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
	@Query("select min(p.polls.size),avg(p.polls.size), stddev(p.polls.size),max(p.polls.size) from Poller p")
	List<Integer> findMinAvgStdMaxPollsByPoller();
	
	@Query("select min(p.instances.size),avg(p.instances.size), stddev(p.instances.size),max(p.instances.size) from Poll p")
	List<Integer> findMinAvgStdMaxInstancesByPoll();
	

}
