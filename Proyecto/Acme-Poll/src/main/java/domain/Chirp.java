
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Chirp extends DomainEntity {

	private String	text;
	private Date	momentWritten;


	public Chirp() {
		super();
	}

	@NotBlank
	@Length(min = 1, max = 140)
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getMomentWritten() {
		return this.momentWritten;
	}

	public void setMomentWritten(final Date momentWritten) {
		this.momentWritten = momentWritten;
	}

}
