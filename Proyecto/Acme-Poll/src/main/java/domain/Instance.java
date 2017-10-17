
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Instance extends DomainEntity {

	public Instance() {
		super();

	}


	private String	name;
	private Gender	gender;
	private String	city;


	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotNull
	public Gender getGender() {
		return this.gender;
	}

	public void setGender(final Gender gender) {
		this.gender = gender;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}


	//	Relathipnships	-----------
	private Actor				actor;
	private Poll				poll;
	private Collection<Answer>	answers;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Poll getPoll() {
		return this.poll;
	}

	public void setPoll(final Poll poll) {
		this.poll = poll;
	}

	@NotNull
	@OneToMany(mappedBy = "instance", cascade = CascadeType.ALL)
	public Collection<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(final Collection<Answer> answers) {
		this.answers = answers;
	}

	public void addAnswer(final Answer answer) {
		this.answers.add(answer);
		answer.setInstance(this);
	}
	public void removeAnswer(final Answer answer) {
		this.answers.remove(answer);
		answer.setInstance(this);
	}

}
