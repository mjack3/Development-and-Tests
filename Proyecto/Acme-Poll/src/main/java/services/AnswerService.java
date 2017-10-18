
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.AnswerRepository;

@Service
@Transactional
public class AnswerService {

	//Manager repositories

	@Autowired
	private AnswerRepository answerRepository;


	//Constructor

	public AnswerService() {
		super();
	}

	//CRUD Methods

}
