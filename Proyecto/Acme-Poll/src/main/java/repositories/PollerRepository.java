
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Poller;

@Repository
public interface PollerRepository extends JpaRepository<Poller, Integer> {

}
