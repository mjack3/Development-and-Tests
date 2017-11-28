
package controllers.poller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Poller;
import security.LoginService;
import services.PollerService;

@Controller
@RequestMapping("/poller")
public class PollerController extends AbstractController {

	@Autowired
	private PollerService	pollerService;
	@Autowired
	private LoginService	loginService;


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
	public ModelAndView view(final Integer pollId) {
		ModelAndView result;

		try {
			result = new ModelAndView("poller/view");

			final Poller p = this.pollerService.findPollerFromPoll(pollId);

			result.addObject("poller", p);

			System.out.println(p);
			System.out.println(pollId);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int userAccountID) {
		ModelAndView result;
		Poller poller;
		try {
			poller = this.pollerService.findActorByUsername(userAccountID);
			final Poller pollerlogin = (Poller) this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
			Assert.isTrue(pollerlogin.getId() == poller.getId());
			result = this.createEditModelAndView(poller);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}

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

		return result;
	}

}
