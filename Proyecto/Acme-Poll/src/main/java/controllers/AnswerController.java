package controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Answer;
import domain.Poll;
import services.InstanceService;
import services.PollService;

@Controller
@RequestMapping("/answer")
public class AnswerController {
	
	@Autowired
	private PollService   	pollService;
	
	@Autowired
	private InstanceService   	instanceService;
	
	private Integer toSave;

	
	@RequestMapping("/answer")
	public ModelAndView answer(@RequestParam Integer q) {
		ModelAndView res;

		res = new ModelAndView("answer/answer");
		
		Poll poll = pollService.findOne(q);
		toSave = q;
		
		res.addObject("question", poll.getQuestions());

		return res;
	}
	
	@RequestMapping(value="/save", method= RequestMethod.POST)
	public ModelAndView save(String data,String gender,String city,String name) {
		ModelAndView res;
		String[] answers = data.substring(1,data.length()).split(",");
		List<Answer> ansToSave = new LinkedList<Answer>();
		Poll p = pollService.findOne(toSave);
		for(int i =0;i<answers.length;i++) {
			Answer a = new Answer();		
			a.setSelected(new Integer(answers[i]));
			a.setQuestion(i+1);
			ansToSave.add(a);
		}
		
		instanceService.save(ansToSave,p,city,gender,name);
		
		res = new ModelAndView("poll/list");
		res.addObject("poll", pollService.findPollActivated());
		return res;
	}
}
