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

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Chirp;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class ChirpTest extends AbstractTest {

	@Autowired
	private ChirpService	chirpService;
	@Autowired
	ActorService			actorService;


	// Tests
	// ====================================================

	protected void listChirpTest(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);

			this.chirpService.findAllPrincipal();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	protected void listOthersChirpsTest(final String username, final Integer idChirp, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);

			this.chirpService.findOne(idChirp);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}
	protected void createChirpTest(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);

			final Chirp chirp = new Chirp();
			chirp.setMomentWritten(new Date(System.currentTimeMillis() - 1));
			chirp.setText("asdas");

			this.chirpService.save(chirp);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	@Test
	public void driverListChirps() {

		final Object testingData[][] = {

			{
				"poller1", null
			}, {
				null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.listChirpTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverCreateChirp() {

		final Object testingData[][] = {

			{
				"poller1", null
			}, {
				null, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.createChirpTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverListOthersChirps() {

		final Object testingData[][] = {

			{// u npoller ve los chirp de otro -> true
				"poller1", 169, null
			}, {//no loged ve chirp de otro -> false
				null, 169, IllegalArgumentException.class
			}, {//poller1 busca chrip que no existe
				"poller1", 45665, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.listOthersChirpsTest((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
