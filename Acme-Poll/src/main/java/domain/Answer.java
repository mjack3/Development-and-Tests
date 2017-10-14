
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

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
}
