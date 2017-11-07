
package services;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Answer;
import domain.Instance;
import domain.Poll;
import repositories.InstanceRepository;

@Service
@Transactional
public class InstanceService {

	//Manager repositories

	@Autowired
	private InstanceRepository instanceRepository;
	
	@Autowired
	private PollService pollService;
	
	@Autowired
	private AnswerService answerService;


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
	
	public Instance save(List<Answer> ansToSave,Poll p,String city, String gender,String name) {
		
		
		Instance ins = new Instance();
		ins.setGender(city);
		ins.setCity(gender);
		ins.setPoll(p);
	
		List<Answer> ansFinal = new LinkedList<Answer>();
		for(Answer a: ansToSave) {
			a = answerService.save(a);
			ansFinal.add(a);
		}
		
		ins.setAnswers(ansFinal);
		ins.setName(name);
		
		List<Instance> insts =(List<Instance>) p.getInstances();
		insts.add(ins);
		p.setInstances(insts);
		
		return instanceRepository.save(ins);
	}

}
