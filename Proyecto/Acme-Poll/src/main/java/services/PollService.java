
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Answer;
import domain.Hint;
import domain.Instance;
import domain.Poll;
import domain.Poller;
import domain.Question;
import repositories.PollRepository;

@Service
@Transactional
public class PollService {

	//Manager repositories

	@Autowired
	private PollRepository pollRepository;

	@Autowired
	private InstanceService instanceService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private HintService		hintService;
	
	@Autowired
	private PollerService		pollerService;

	//Constructor

	public PollService() {
		super();
	}

	//CRUD Methods

	public List<Poll> findAll() {
		return this.pollRepository.findAll();
	}

	public Poll findOne(final Integer arg0) {
		Assert.notNull(arg0);
		return this.pollRepository.findOne(arg0);
	}

	public List<Poll> findPollActivated() {
		Date now = Calendar.getInstance().getTime();

		//DateFormat.getDateInstance().format(now);

		//System.out.print(now.toString());

		return this.pollRepository.findPollActivated(now);

	}
	public String findMinAvgStdMaxPollsByPoller(){
		String res = "Min: " + pollRepository.findMinAvgStdMaxPollsByPoller()[0] 
				+ ", Avg: " +  pollRepository.findMinAvgStdMaxPollsByPoller()[1]
						+ ", Std: " +  pollRepository.findMinAvgStdMaxPollsByPoller()[2]
								+ ", Max: " +  pollRepository.findMinAvgStdMaxPollsByPoller()[3];
		return res;
		
	}
	public String findMinAvgStdMaxInstancesByPoll(){
		String res = "Min: " + pollRepository.findMinAvgStdMaxInstancesByPoll()[0] 
				+ ", Avg: " +  pollRepository.findMinAvgStdMaxInstancesByPoll()[1]
						+ ", Std: " +  pollRepository.findMinAvgStdMaxInstancesByPoll()[2]
								+ ", Max: " +  pollRepository.findMinAvgStdMaxInstancesByPoll()[3];
		return res;
	}

	public void delete(Poll arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(pollRepository.exists(arg0.getId()));
		
		for(Instance instance: arg0.getInstances()) {
			instanceService.delete(instance.getId());
		}
		
		arg0.setInstances(new ArrayList<Instance>());
		
		for(Question question: arg0.getQuestions()) {
			questionService.delete(question.getId());
		}
		
		arg0.setQuestions(new ArrayList<Question>());
		
		for(Hint hint: arg0.getHints()) {
			hintService.delete(hint.getId());
		}
		
		arg0.setHints(new ArrayList<Hint>());
		
		Poller poller = arg0.getPoller();
		
		List<Poll> polls = (List<Poll>) poller.getPolls();
		polls.remove(arg0);
		poller.setPolls(polls);
		
		pollerService.save(poller);
		
		arg0.setPoller(new Poller());
		
		pollRepository.delete(arg0);
	}

	public Poll update(Poll arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(pollRepository.exists(arg0.getId()));
		return pollRepository.save(arg0);
	}

	public String findMinAvgStdMaxQuestionByPoll() {
		String res = "Min: " + pollRepository.findMinAvgStdMaxQuestionByPoll()[0] 
				+ ", Avg: " +  pollRepository.findMinAvgStdMaxQuestionByPoll()[1]
						+ ", Std: " +  pollRepository.findMinAvgStdMaxQuestionByPoll()[2]
								+ ", Max: " +  pollRepository.findMinAvgStdMaxQuestionByPoll()[3];
		return res;
	}

	public List<Integer> getResults(Integer q) {
		
		Poll poll = pollRepository.findOne(q);
		Map<Integer,List<Integer>> res = new HashMap<Integer,List<Integer>>();
		List<Question> questionsTotal =(List<Question>) poll.getQuestions();
		for(int i = 0; i<questionsTotal.size();i++) {
			Integer quest= i;
			Integer cont = questionsTotal.get(0).getChoices().size();
			for(int b=0;b<cont;b++) {
				if(res.get(quest)==null || res.get(quest).isEmpty()) {
					List<Integer> choicess = new LinkedList<Integer>();
					choicess.add(b, new Integer(0));
					res.put(quest, choicess);
				}else {
					List<Integer> choices = res.get(quest);
					choices.add(b, new Integer(0));
					res.put(quest, choices);
				}
			}
			
		}
		
 		for(Instance instance: poll.getInstances()) {
			for(Answer answer: instance.getAnswers()) {
				Integer question = answer.getQuestion()-1;
				Integer choice = answer.getSelected();
				List<Integer> aux = res.get(question);			
				Integer valAux=aux.get(choice);
				valAux++;
				aux.set(choice, valAux);
				res.put(question, aux);		
			}
		}
 		
 		List<Integer> resfinal = new LinkedList<Integer>();
 		
 		for(int c=0;c<questionsTotal.size();c++) {
 			resfinal.addAll(res.get(new Integer(c)));
 		}
 		
 		return resfinal;
	}
	
	
	

}
