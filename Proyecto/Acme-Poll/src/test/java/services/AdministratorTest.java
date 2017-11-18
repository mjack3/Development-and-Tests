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
import domain.Administrator;

@Transactional
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AdministratorTest extends AbstractTest {

	@Autowired
	private AdministratorService administratorService;

	// Tests
	// ====================================================

	protected void editAdministratorTest(String username, String phone, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			authenticate(username);
			Administrator Administrator = administratorService.findActorByUsername(LoginService
					.getPrincipal().getId());
			Administrator.setPhone(phone);
			administratorService.saveAF(Administrator);
			unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);

	}

	@Test
	public void driverEditAdministrator() {

		Object testingData[][] = {

		{ "admin", "+34(68)687975531", null },
		{ "admin", "abcd", ConstraintViolationException.class }
		};

		for (int i = 0; i < testingData.length; i++) {
			editAdministratorTest((String) testingData[i][0], (String) testingData[i][1],
					(Class<?>) testingData[i][2]);
		}
	}
	
	protected void banPollerTest(String username,int pollerId, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			authenticate(username);
			administratorService.bannedPoller(pollerId);
			
			unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);

	}
	
	@Test
	public void driverBanPoller() {

		Object testingData[][] = {
			// El Administrator logueado todo correcto. -> true
			{
				"admin",90, null
			},
			// Estamos autenticados pero el user es incorrecto -> false
			{
				"admin", 999999,IllegalArgumentException.class
			}, {
				// Si no estamos autentificados como admin -> false
				null, 90, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			banPollerTest((String) testingData[i][0], (int) testingData[i][1],(Class<?>) testingData[i][2]);
		}
	}
	
	

}
