
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity {

	public Question() {
	}


	private Integer			number;
	private String			statment;
	private List<String>	choices;


	@NotBlank
	public String getStatment() {
		return this.statment;
	}

	public void setStatment(final String statment) {
		this.statment = statment;
	}

	@NotNull
	@Range(min = 1)
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(final Integer number) {
		this.number = number;
	}

	@NotNull
	@ElementCollection(targetClass = String.class)
	public List<String> getChoices() {
		return this.choices;
	}

	public void setChoices(final List<String> choices) {
		this.choices = choices;
	}


	// Relathionships	--------

	private Poll	poll;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Poll getPoll() {
		return this.poll;
	}

	public void setPoll(final Poll poll) {
		this.poll = poll;
	}

}
