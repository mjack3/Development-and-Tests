package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Hint;
import repositories.HintRepository;

@Service
@Transactional
public class HintService {

	@Autowired
	private HintRepository hintRepository;
	
	public HintService() {
		super();
	}

	public void delete(Integer arg0) {
		Assert.notNull(arg0);
//		Assert.isTrue(hintRepository.exists(arg0));
		hintRepository.delete(arg0);
	}

	public List<Hint> findAll() {
		return hintRepository.findAll();
	}

	public Hint findOne(Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(hintRepository.exists(arg0));
		return hintRepository.findOne(arg0);
	}

	public Hint save(Hint arg0) {
		Assert.notNull(arg0);
		return hintRepository.save(arg0);
	}
	
	public Hint update(Hint arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(hintRepository.exists(arg0.getId()));
		return hintRepository.save(arg0);
	}
}
