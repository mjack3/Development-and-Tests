
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import domain.Actor;
import domain.Administrator;
import domain.Poller;

@Service
@Transactional
public class ActorService {

	//Manager repositories

	@Autowired
	private ActorRepository			actorRepository;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private PollerService			pollerService;


	//Constructor

	public ActorService() {
		super();
	}

	//CRUD Methods

	public void delete(final Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.actorRepository.exists(arg0));
		this.actorRepository.delete(arg0);
	}

	public List<Actor> findAll() {
		return this.actorRepository.findAll();
	}

	public Actor findOne(final Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.actorRepository.exists(arg0));
		return this.actorRepository.findOne(arg0);
	}

	public Actor save(final Actor arg0) {
		Assert.notNull(arg0);
		return this.actorRepository.save(arg0);
	}

	public Actor update(final Actor arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.actorRepository.exists(arg0.getId()));
		return this.actorRepository.save(arg0);
	}

	public Actor getActual() {
		Actor res = null;

		final Administrator a = this.administratorService.findActorByUsername(LoginService.getPrincipal().getId());
		final Poller p = this.pollerService.findActorByUsername(LoginService.getPrincipal().getId());

		if (a != null)
			res = a;
		else
			res = p;

		return res;
	}

	public Actor findOnePrincipal(final int id) {
		// TODO Auto-generated method stub
		return this.actorRepository.findOnePrincipal(id);
	}
}
