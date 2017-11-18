/*
 * AbstractTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.LoginService;
import utilities.AbstractTest;
import domain.Poller;

@Transactional
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PollerTest extends AbstractTest {

	@Autowired
	private PollerService pollerService;

	// Tests
	// ====================================================

	protected void editPollerTest(String username, String phone, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username);
			Poller poller = pollerService.findActorByUsername(LoginService
					.getPrincipal().getId());
			poller.setPhone(phone);
			pollerService.saveAF(poller);
			unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);

	}

	@Test
	public void driverEditPoller() {

		Object testingData[][] = {

		{ "poller1", "+34(68)687975531", null },
		{ "poller1", "abcd", ConstraintViolationException.class }
		};

		for (int i = 0; i < testingData.length; i++) {
			editPollerTest((String) testingData[i][0], (String) testingData[i][1],
					(Class<?>) testingData[i][2]);
		}
	}

}
