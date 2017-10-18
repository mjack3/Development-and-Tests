
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.QuestionRepository;

@Service
@Transactional
public class QuestionService {

	//Manager repositories

	@Autowired
	private QuestionRepository questionRepository;


	//Constructor

	public QuestionService() {
		super();
	}

	//CRUD Methods

}
