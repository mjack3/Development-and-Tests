
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Answer extends DomainEntity {

	public Answer() {
		super();
	}


	private int		number;	// Every answer must correspond to a question in the associated Poll
	private Integer	selected;	//	Answer selected in the asscoiated Poll


	@Min(0)
	public Integer getSelected() {
		return this.selected;
	}

	public void setSelected(final Integer selected) {
		this.selected = selected;
	}

	@Min(0)
	public int getNumber() {
		return this.number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}


	// Relathionships	--------

	private Instance	instance;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Instance getInstance() {
		return this.instance;
	}

	public void setInstance(final Instance instance) {
		this.instance = instance;
	}
}
