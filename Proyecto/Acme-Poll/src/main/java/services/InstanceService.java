
package services;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.InstanceRepository;
import domain.Answer;
import domain.Instance;
import domain.Poll;

@Service
@Transactional
public class InstanceService {

	//Manager repositories

	@Autowired
	private InstanceRepository	instanceRepository;

	@Autowired
	private PollService			pollService;

	@Autowired
	private AnswerService		answerService;


	//Constructor

	public InstanceService() {
		super();
	}

	//CRUD Methods

	public void delete(final Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.instanceRepository.exists(arg0));
		this.instanceRepository.delete(arg0);
	}

	public List<Instance> findAll() {
		return this.instanceRepository.findAll();
	}

	public Instance findOne(final Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.instanceRepository.exists(arg0));
		return this.instanceRepository.findOne(arg0);
	}

	public Instance save(final Instance arg0) {
		Assert.notNull(arg0);
		return this.instanceRepository.save(arg0);
	}

	public Instance update(final Instance arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.instanceRepository.exists(arg0.getId()));
		return this.instanceRepository.save(arg0);
	}

	public Instance save(final List<Answer> ansToSave, final Poll p, final String city, final String gender, final String name) {

		final Instance ins = new Instance();
		ins.setGender(city);
		ins.setCity(gender);
		ins.setPoll(p);

		//	se genera un tipo Instance v�lido

		final List<Answer> ansFinal = new LinkedList<Answer>();
		for (Answer a : ansToSave) {
			a = this.answerService.save(a);
			ansFinal.add(a);
		}

		ins.setAnswers(ansFinal);
		ins.setName(name);

		//	Guardado y actualizaci�n de referencias

		final Instance saved = this.instanceRepository.save(ins);

		p.getInstances().add(ins);
		this.pollService.save(p);

		return saved;
	}

}
