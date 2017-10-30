
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity {

	public Question() {
	}

	private String				statment;
	private Collection<Choice>	choices;

	@NotBlank
	public String getStatment() {
		return this.statment;
	}

	public void setStatment(final String statment) {
		this.statment = statment;
	}

	@NotNull
	@NotEmpty
	@OneToMany
	public Collection<Choice> getChoices() {
		return choices;
	}

	public void setChoices(Collection<Choice> choices) {
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
