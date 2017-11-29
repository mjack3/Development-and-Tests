
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.PollerService;

@RequestMapping("/poller/administrator")
@Controller
public class PollerAdministratorController {

	@Autowired
	private PollerService			pollerService;

	@Autowired
	private AdministratorService	administratorService;


	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView res;

		res = new ModelAndView("poller/list");

		res.addObject("poller", this.pollerService.findAll());
		res.addObject("bannedPollers", this.pollerService.pollersBanned());
		res.addObject("isPoller", false);

		return res;
	}

	@RequestMapping("/banned")
	public ModelAndView banned(@RequestParam final int q) {

		try {
			this.administratorService.bannedPoller(q);
			return this.list();
		} catch (final Exception e) {
			return this.list();
		}

	}

	@RequestMapping("/readmit")
	public ModelAndView readmit(@RequestParam final int q) {

		try {
			this.administratorService.readmitPoller(q);
			return this.list();
		} catch (final Exception e) {
			return this.list();
		}

	}
}
