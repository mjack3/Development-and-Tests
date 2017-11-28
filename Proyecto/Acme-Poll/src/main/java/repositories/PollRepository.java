
package repositories;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {

	@Query("select p from Poll p where p.startDate <= ?1 and p.endDate >= ?1")
	List<Poll> findPollActivated(Date today);

	@Query("select min(p.polls.size),avg(p.polls.size), stddev(p.polls.size),max(p.polls.size) from Poller p")
	Double[] findMinAvgStdMaxPollsByPoller();

	@Query("select min(p.instances.size),avg(p.instances.size), stddev(p.instances.size),max(p.instances.size) from Poll p")
	Double[] findMinAvgStdMaxInstancesByPoll();

	@Query("select min(p.questions.size),avg(p.questions.size), stddev(p.questions.size),max(p.questions.size) from Poll p")
	Double[] findMinAvgStdMaxQuestionByPoll();

	@Query("select min(p.hints.size),avg(p.hints.size), max(p.hints.size) from Poll p")
	Double[] findMinAvgMaxHintsByPoll();

	@Query("select p from Poll p where p.hints.size = (select max(o.hints.size) from Poll o)")
	List<Poll> findPollWithMoreHints();

	@Query("select p from Poll p where p.hints.size = (select min(o.hints.size) from Poll o)")
	List<Poll> findPollWithFewerHints();

	@Query("select p from Poll p join p.hints h where h.mark >= (select avg(t.mark) from Poll l join l.hints t)")
	List<Poll> findPollWithHintsAbogeAverage();

	@Query("select a, ((a.chirps.size * 1.0) / (select count(c) from Chirp c)) from Actor a")
	List<Array[]> avgChirps();

	@Query("select a from Actor a where (select avg(b.chirps.size)from Actor b)< a.chirps.size*1.0/(select count(c) from Chirp c)")
	List<Actor> actorsAboveAvg();

}
