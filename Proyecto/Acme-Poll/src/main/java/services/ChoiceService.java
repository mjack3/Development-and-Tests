package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Choice;
import repositories.ChoiceRepository;

@Service
@Transactional
public class ChoiceService {
	
	@Autowired
	private ChoiceRepository choiceRepository;
	
	public ChoiceService() {
		super();
	}

	public void delete(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(choiceRepository.exists(arg0));
		choiceRepository.delete(arg0);
	}

	public List<Choice> findAll() {
		return choiceRepository.findAll();
	}

	public Choice findOne(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(choiceRepository.exists(arg0));
		return choiceRepository.findOne(arg0);
	}

	public Choice save(Choice arg0) {
		Assert.notNull(arg0);
		return choiceRepository.save(arg0);
	}
	
	public Choice update(Choice arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(choiceRepository.exists(arg0.getId()));
		return choiceRepository.save(arg0);
	}

}
