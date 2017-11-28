
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Hint;
import domain.Poll;
import services.HintService;
import services.PollService;

@Controller
@RequestMapping("/hint")
public class HintController {

	@Autowired
	private PollService	pollService;

	@Autowired
	private HintService	hintService;

	private Integer		toSave;


	@RequestMapping("/list")
	public ModelAndView list(@RequestParam final Integer q) {
		ModelAndView res;

		try {
			res = new ModelAndView("hint/list");

			final Poll poll = this.pollService.findOne(q);

			res.addObject("hint", poll.getHints());
			res.addObject("pollId", q);
		} catch (final Throwable e) {
			res = new ModelAndView("redirect:/welcome/index.do");
		}

		return res;
	}

	@RequestMapping("/create")
	public ModelAndView create(@RequestParam final Integer q) {
		ModelAndView res;
		try {
			res = new ModelAndView("hint/create");

			res.addObject("hint", this.hintService.create());
			this.toSave = q;
		} catch (final Throwable e) {
			res = new ModelAndView("redirect:/welcome/index.do");
		}

		return res;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Hint hint, final BindingResult binding) {
		final Integer to = this.toSave;
		if (binding.hasErrors())
			this.list(to);
		else
			try {
				this.hintService.save(hint, to);
				return this.list(to);
			} catch (final Exception e) {
				return this.list(to);
			}
		this.toSave = null;
		return this.list(to);

	}

	@RequestMapping("/score")
	public ModelAndView score(@RequestParam final Integer q, @RequestParam final Integer p) {
		ModelAndView res;
		try {
			res = new ModelAndView("hint/score");

			res.addObject("hint", q);
			res.addObject("pollId", p);
			this.toSave = p;
		} catch (final Throwable e) {
			res = new ModelAndView("redirect:/welcome/index.do");
		}

		return res;
	}

	@RequestMapping(value = "/score", method = RequestMethod.POST)
	public ModelAndView scoreSave(@RequestParam final Integer q, @RequestParam final Integer t) {
		final Integer to = this.toSave;
		try {
			this.hintService.score(q, t);
			return this.list(to);
		} catch (final Exception e) {
			return this.list(to);
		}

	}

	@RequestMapping(value = "/remove")
	public ModelAndView remove(@RequestParam final Integer q, @RequestParam final Integer p) {
		try {
			this.hintService.remove(q, p);
			return this.list(p);
		} catch (final Exception e) {
			return this.list(p);
		}

	}

}
