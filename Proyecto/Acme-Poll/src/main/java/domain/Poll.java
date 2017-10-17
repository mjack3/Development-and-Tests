
package domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Poll extends DomainEntity {

	public Poll() {
		super();

		this.questions = new HashSet<Question>();	// Min initialization + Question must be different
		this.instances = new HashSet<Instance>();		// Min initialization 
	}


	private String	title;
	private String	ticket;
	private String	description;
	private String	banner;
	private Date	timeActive;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@Column(unique = true)
	@Pattern(regexp = "^[a-zA-Z]{2}-\\d{5}$")
	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(final String ticket) {
		this.ticket = ticket;
	}

	@NotNull
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@URL
	@NotBlank
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getTimeActive() {
		return this.timeActive;
	}

	public void setTimeActive(final Date timeActive) {
		this.timeActive = timeActive;
	}


	//	Relationships	--------------

	private Collection<Question>	questions;
	private Collection<Instance>	instances;
	private Poller					poller;


	@NotNull
	@OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
	public Collection<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(final Collection<Question> questions) {
		this.questions = questions;
	}
	public void addQuestion(final Question question) {
		this.questions.add(question);
		question.setPoll(this);
	}
	public void removeQuestion(final Question question) {
		this.questions.remove(question);
		question.setPoll(this);
	}

	@NotNull
	@OneToMany(mappedBy = "poll")
	public Collection<Instance> getInstances() {
		return this.instances;
	}

	public void setInstances(final Collection<Instance> instances) {
		this.instances = instances;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Poller getPoller() {
		return this.poller;
	}

	public void setPoller(final Poller poller) {
		this.poller = poller;
	}

}
