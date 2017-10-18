
package controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Poll;
import services.PollService;

@Controller
@RequestMapping("/poll")
public class PollController extends AbstractController {

	@Autowired
	private PollService pollService;


	public PollController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Date now = new Date();
		result = new ModelAndView("poll/list");
		result.addObject("requestURI", "poll/list.do");
		final List<Poll> catalogue = this.pollService.findPollActivated();
		result.addObject("poll", catalogue);
		result.addObject("timeActive", now);
		return result;
	}

}
