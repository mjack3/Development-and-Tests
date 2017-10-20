
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Administrator;
import repositories.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {

	//Manager repositories

	@Autowired
	private AdministratorRepository administratorRepository;


	//Constructor

	public AdministratorService() {
		super();
	}

	//CRUD Methods

	public Administrator save(final Administrator administrator) {
		Assert.notNull(administrator);
		Administrator a = null;

		if (this.exists(administrator.getId())) {
			a = this.findOne(administrator.getId());
			a.setName(administrator.getName());
			a.setEmail(administrator.getEmail());
			a.setPhone(administrator.getPhone());
			a.setSurname(administrator.getSurname());
			a = this.administratorRepository.save(a);
		} else {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			administrator.getUserAccount().setPassword(encoder.encodePassword(administrator.getUserAccount().getPassword(), null));
			a = this.administratorRepository.save(administrator);
		}
		return a;
	}

	public List<Administrator> findAll() {
		return this.administratorRepository.findAll();
	}

	public Administrator findOne(final Integer arg0) {
		Assert.notNull(arg0);
		return this.administratorRepository.findOne(arg0);
	}

	public boolean exists(final Integer arg0) {
		Assert.notNull(arg0);
		return this.administratorRepository.exists(arg0);
	}

	public Administrator findActorByUsername(final Integer id) {
		Assert.notNull(id);
		return this.administratorRepository.findOneUserAccount(id);
	}

}