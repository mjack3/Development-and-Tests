
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {

}
