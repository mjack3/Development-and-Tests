package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Choice;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Integer> {

}
