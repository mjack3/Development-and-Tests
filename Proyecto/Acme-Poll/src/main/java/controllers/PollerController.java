
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Poller;
import services.PollerService;

@RequestMapping("/poller")
@Controller
public class PollerController {

	@Autowired
	private PollerService pollerService;


	/*
	 * @RequestMapping(value = "/view", method = RequestMethod.GET)
	 * public ModelAndView view(final Poller poller, final Integer id) {
	 * ModelAndView res;
	 *
	 * res = new ModelAndView("poller/view");
	 * res.addObject("requestURI", "poll/view.do");
	 * res.addObject("poller", this.pollerService.findPollerFromPoll(id));
	 * res.addObject("poller", poller);
	 *
	 * return res;
	 * }
	 */

	@RequestMapping("/view")
	public ModelAndView view(@RequestParam final Poller q) {
		ModelAndView res;

		res = new ModelAndView("poller/view");
		res.addObject("poller", q);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int userAccountID) {
		ModelAndView result;
		Poller poller;

		poller = this.pollerService.findActorByUsername(userAccountID);

		result = this.createEditModelAndView(poller);

		return result;
	}

	@RequestMapping(value = "/save-poller", method = RequestMethod.POST, params = "save")
	public ModelAndView savePoller(@Valid final Poller poller, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			for (final ObjectError e : binding.getAllErrors())
				System.out.println(e.toString());
			result = this.createEditModelAndView(poller, "poller.commit.error");
		} else
			try {
				this.pollerService.save(poller);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable th) {
				th.printStackTrace();
				result = this.createEditModelAndView(poller, "poller.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Poller poller) {

		ModelAndView result;
		result = this.createEditModelAndView(poller, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Poller poller, final String message) {

		ModelAndView result;

		result = new ModelAndView("poller/edit");
		result.addObject("poller", poller);
		result.addObject("message", message);

		System.out.println(message);

		return result;
	}

}
