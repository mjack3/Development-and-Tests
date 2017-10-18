
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.PollerRepository;

@Service
@Transactional
public class PollService {

	//Manager repositories

	@Autowired
	private PollerRepository pollerRepository;


	//Constructor

	public PollService() {
		super();
	}

	//CRUD Methods

}
