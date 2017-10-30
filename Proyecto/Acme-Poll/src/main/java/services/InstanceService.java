
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Instance;
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
	
	public void delete(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(instanceRepository.exists(arg0));
		instanceRepository.delete(arg0);
	}

	public List<Instance> findAll() {
		return instanceRepository.findAll();
	}

	public Instance findOne(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(instanceRepository.exists(arg0));
		return instanceRepository.findOne(arg0);
	}

	public Instance save(Instance arg0) {
		Assert.notNull(arg0);
		return instanceRepository.save(arg0);
	}
	
	public Instance update(Instance arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(instanceRepository.exists(arg0.getId()));
		return instanceRepository.save(arg0);
	}

}
