
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.InstanceRepository;

@Service
@Transactional
public class InstanceService {

	//Manager repositories

	@Autowired
	private InstanceRepository instanceRepository;


	//Constructor

	public InstanceService() {
		super();
	}

	//CRUD Methods

}
