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
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class AdministratorTest extends AbstractTest {

	@Autowired
	private AdministratorService	administratorService;


	// Tests
	// ====================================================

	protected void editAdministratorTest(final String username, final String phone, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			final Administrator Administrator = this.administratorService.findActorByUsername(LoginService.getPrincipal().getId());
			Administrator.setPhone(phone);
			this.administratorService.saveAF(Administrator);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	@Test
	public void driverEditAdministrator() {

		final Object testingData[][] = {

			{
				"admin", "+34(68)687975531", null
			}, {
				"admin", "abcd", ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.editAdministratorTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void banPollerTest(final String username, final int pollerId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			this.administratorService.bannedPoller(pollerId);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	@Test
	public void driverBanPoller() {

		final Object testingData[][] = {
			// El Administrator logueado todo correcto. -> true
			{
				"admin", 93, null
			},
			// Estamos autenticados pero el user es incorrecto -> false
			{
				"admin", 999999, NullPointerException.class
			}, {
				// Si no estamos autentificados como admin -> false
				null, 93, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.banPollerTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
