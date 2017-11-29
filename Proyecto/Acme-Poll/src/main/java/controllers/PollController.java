
package controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Poll;
import services.PollService;
import services.ValidPeriodService;

@Controller
@RequestMapping("/poll")
public class PollController extends AbstractController {

	@Autowired
	private PollService			pollService;

	@Autowired
	private ValidPeriodService	validPeriodService;


	public PollController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Date actualDate = Calendar.getInstance().getTime();
		result = new ModelAndView("poll/list");
		result.addObject("requestURI", "poll/list.do");
		final List<Poll> catalogue = this.pollService.findPollActivated();
		result.addObject("poll", catalogue);
		result.addObject("actualDate", actualDate);
		result.addObject("validPeriod", this.validPeriodService.get().getMinimumPeriod());
		result.addObject("isPoller", false);
		return result;
	}

	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public ModelAndView results(@RequestParam final Integer q) {
		ModelAndView result;
		try {
			final List<Integer> list = this.pollService.getResults(q);

			result = new ModelAndView("poll/results");
			result.addObject("results", list);
			result.addObject("question", this.pollService.findOne(q).getQuestions());
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

}
