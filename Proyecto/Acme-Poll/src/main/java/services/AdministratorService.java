
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
