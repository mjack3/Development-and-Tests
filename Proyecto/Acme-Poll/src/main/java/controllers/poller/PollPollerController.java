package controllers.poller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Poll;
import domain.Poller;
import security.LoginService;
import services.PollService;
import services.PollerService;

@Controller
@RequestMapping("/poll/poller")
public class PollPollerController {

	@Autowired
	private PollerService pollerService;
	
	@Autowired
	private PollService pollService;
	
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView res;

		res = new ModelAndView("poll/list");
		
		Poller poller = pollerService.findActorByUsername(LoginService.getPrincipal().getId());
		
		res.addObject("poll", poller.getPolls());

		return res;
	}
	
	@RequestMapping("/remove")
	public ModelAndView remove(@RequestParam Integer q) {

		try {
			Poll poll = pollService.findOne(q);
			pollService.delete(poll);
			return list();
		}catch (Exception e) {
			return list();
		}

	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam Integer q) {
		ModelAndView res;

		res = new ModelAndView("poll/edit");
		
		Poll poll = pollService.findOne(q);
		
		res.addObject("poll", poll);

		return res;

	}
	
	@RequestMapping("/save")
	public ModelAndView save(@Valid Poll poll) {
		try {
			pollService.update(poll);
			return edit(poll.getId());
		}catch (Exception e) {
			return edit(poll.getId());
		}

	}
	
}
