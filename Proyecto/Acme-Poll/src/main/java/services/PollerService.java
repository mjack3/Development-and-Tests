
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Poller;
import repositories.PollerRepository;

@Service
@Transactional
public class PollerService {

	//Manager repositories

	@Autowired
	private PollerRepository pollerRepository;


	//Constructor

	public PollerService() {
		super();
	}

	//CRUD Methods

	public List<Poller> findAll() {
		return this.pollerRepository.findAll();
	}

	public Poller findOne(final Integer arg0) {
		Assert.notNull(arg0);
		return this.pollerRepository.findOne(arg0);
	}

}
