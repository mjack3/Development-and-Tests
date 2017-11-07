
package controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Poll;
import services.PollService;

@Controller
@RequestMapping("/poll")
public class PollController extends AbstractController {

	@Autowired
	private PollService	pollService;


	public PollController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Date actualDate = Calendar.getInstance().getTime();
		result = new ModelAndView("poll/list");
		result.addObject("requestURI", "poll/list.do");
		final List<Poll> catalogue = pollService.findPollActivated();
		result.addObject("poll", catalogue);
		result.addObject("actualDate", actualDate);
		return result;
	}
	
	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public ModelAndView results(@RequestParam Integer q) {
		ModelAndView result;

		List<Integer> list = pollService.getResults(q);
		
		result = new ModelAndView("poll/results");
		result.addObject("results", list);
		result.addObject("question", pollService.findOne(q).getQuestions());

		return result;
	}

}
