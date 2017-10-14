
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity {

	public Question() {
		super();

		this.answers = new HashSet<String>();	//	the possibles answer must be differents
	}


	private int							number;
	private String						statment;
	private final Collection<String>	answers;


	@Min(0)
	public int getNumber() {
		return this.number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}

	@NotBlank
	public String getStatment() {
		return this.statment;
	}

	public void setStatment(final String statment) {
		this.statment = statment;
	}

	@NotEmpty
	@ElementCollection
	public Collection<String> getAnswers() {
		return this.answers;
	}
	public void addAnswer(final String answer) {
		this.answers.add(answer);
	}

	public void removeAnswer(final String answer) {
		this.answers.remove(answer);
	}

	// Relathionships	--------
}
