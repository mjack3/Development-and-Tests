
package services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrator;
import domain.Chirp;
import domain.Poller;
import repositories.ChirpRepository;
import security.Authority;
import security.LoginService;

@Service
@Transactional
public class ChirpService {

	@Autowired
	private ChirpRepository			chirpRepository;

	//Services
	@Autowired
	private PollerService			pollerService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private LoginService			loginService;


	//Constructor
	public ChirpService() {
		super();
	}

	//CRUD Methods

	public Chirp create() {
		final Chirp chirp = new Chirp();

		final Actor a = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());

		chirp.setText(new String());

		chirp.setMomentWritten(new Date());

		return chirp;
	}
	public void delete(final Chirp chirp) {
		Assert.notNull(chirp);

		this.chirpRepository.delete(chirp);
	}

	public List<Chirp> findAll() {
		return this.chirpRepository.findAll();
	}

	public Chirp findOne(final int id) {
		return this.chirpRepository.findOne(id);
	}

	public Chirp save(Chirp chirp) {
		Assert.notNull(chirp);

		final Actor a = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());

		chirp = this.chirpRepository.save(chirp);

		final List<Chirp> chirpsActor = a.getChirps();
		chirpsActor.add(chirp);
		a.setChirps(chirpsActor);

		if (a.getUserAccount().getAuthorities().contains(Authority.POLLER))
			this.pollerService.save((Poller) a);

		else if (a.getUserAccount().getAuthorities().contains(Authority.ADMINISTRATOR))
			this.administratorService.save((Administrator) a);

		return this.chirpRepository.save(chirp);
	}

	public List<Actor> actorChirps() {
		return this.chirpRepository.actorChirps();
	}

	//Other Methods

}
