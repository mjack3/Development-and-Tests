/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private LoginService	loginService;


	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required = false, defaultValue = "John Doe") final String name, @CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter, final HttpServletResponse responde) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");

		//increment hitCounter
		hitCounter++;
		// create cookie and set it in response
		final Cookie cookie = new Cookie("hitCounter", hitCounter.toString());

		responde.addCookie(cookie);

		//result.addObject("cookie", cookie);

		result.addObject("name", name);

		result.addObject("moment", moment);

		return result;
	}

	@RequestMapping(value = "/cookies")
	public ModelAndView cookies() {
		ModelAndView result;

		result = new ModelAndView("legislation/cookies");

		return result;
	}

	@RequestMapping(value = "/lopd")
	public ModelAndView lopd() {
		ModelAndView result;

		result = new ModelAndView("legislation/lopd");

		return result;
	}

	@RequestMapping(value = "/lssi")
	public ModelAndView lssi() {
		ModelAndView result;

		result = new ModelAndView("legislation/lssi");

		return result;
	}
}
