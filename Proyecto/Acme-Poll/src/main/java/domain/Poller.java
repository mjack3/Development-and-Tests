
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Poller extends Actor {
	
	public Poller() {
		super();
		this.polls = new HashSet<Poll>();
	}

	//	RelationShips	----------


	private Collection<Poll>	polls;
	
	@NotNull
	@OneToMany(mappedBy = "poller")
	public Collection<Poll> getPolls() {
		return this.polls;
	}

	public void setPolls(final Collection<Poll> polls) {
		this.polls = polls;
	}

}
