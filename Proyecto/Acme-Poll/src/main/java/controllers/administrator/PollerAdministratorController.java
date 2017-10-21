package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.PollerService;
import domain.Poller;

@RequestMapping("/poller/administrator")
@Controller
public class PollerAdministratorController {

	@Autowired
	private PollerService pollerService;
	
	@Autowired
	private AdministratorService administratorService;

	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView res;

		res = new ModelAndView("poller/list");
		
		res.addObject("poll", pollerService.findAll());
		res.addObject("bannedPollers", pollerService.pollersBanned());

		return res;
	}
	
	@RequestMapping("/banned")
	public ModelAndView banned(@RequestParam Poller q) {
		
		try {
			administratorService.bannedPoller(q);
			return list();
		}catch (Exception e) {
			return list();
		}

	}
	
	@RequestMapping("/readmit")
	public ModelAndView readmit(@RequestParam Poller q) {
		
		try {
			administratorService.readmitPoller(q);
			return list();
		}catch (Exception e) {
			return list();
		}

	}
}
