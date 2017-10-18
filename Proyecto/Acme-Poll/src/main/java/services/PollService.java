
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Poll;
import repositories.PollRepository;

@Service
@Transactional
public class PollService {

	//Manager repositories

	@Autowired
	private PollRepository pollRepository;


	//Constructor

	public PollService() {
		super();
	}

	//CRUD Methods

	public List<Poll> findAll() {
		return this.pollRepository.findAll();
	}

	public Poll findOne(final Integer arg0) {
		Assert.notNull(arg0);
		return this.pollRepository.findOne(arg0);
	}

}
