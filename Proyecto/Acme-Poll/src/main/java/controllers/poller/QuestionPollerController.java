
package controllers.poller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Poll;
import domain.Question;
import services.PollService;
import services.QuestionService;

@Controller
@RequestMapping("/question/poller")
public class QuestionPollerController {

	@Autowired
	private PollService		pollService;

	@Autowired
	private QuestionService	questionService;


	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final Integer q) {
		ModelAndView res;

		res = new ModelAndView("question/list");
		try {
			final Poll poll = this.pollService.findOne(q);

			res.addObject("question", poll.getQuestions());
		} catch (final Throwable e) {
			res = new ModelAndView("redirect:/welcome/index.do");
		}

		return res;
	}

	@RequestMapping("/listChoice")
	public ModelAndView listChoice(@RequestParam final Integer q) {
		ModelAndView res;

		res = new ModelAndView("choice/list");
		try {
			final Question question = this.questionService.findOne(q);

			res.addObject("choice", question.getChoices());
		} catch (final Throwable e) {
			res = new ModelAndView("redirect:/welcome/index.do");
		}

		return res;
	}
}
