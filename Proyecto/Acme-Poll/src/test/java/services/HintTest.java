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

import utilities.AbstractTest;
import domain.Hint;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class HintTest extends AbstractTest {

	@Autowired
	private HintService	hintService;


	// Tests
	// ====================================================

	protected void writeHintTest(final String username, final String text, final int pollId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			final Hint hint = this.hintService.create();
			hint.setText(text);
			this.hintService.save(hint, pollId);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	@Test
	public void driverWriteHint() {

		final Object testingData[][] = {

			{
				"poller1", "texto del hint", 98, null
			}, {
				"poller1", "", 98, ConstraintViolationException.class
			}, {
				"poller1", "texto del hint", 951111, ConstraintViolationException.class
			}, {
				null, "texto del hint", 98, ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.writeHintTest((String) testingData[i][0], (String) testingData[i][1], (int) testingData[i][2], (Class<?>) testingData[i][3]);
	}
	protected void scoreHintTest(final String username, final int hintId, final int score, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			this.hintService.score(hintId, score);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	@Test
	public void driverScoreHint() {

		final Object testingData[][] = {

			{
				"poller1", 161, 9, null
			}, {
				"poller1", 1581111, 9, IllegalArgumentException.class
			}, {
				null, 161, 9, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.scoreHintTest((String) testingData[i][0], (int) testingData[i][1], (int) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void deleteHintTest(final String username, final int hintId, final int pollId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);

			this.hintService.remove(hintId, pollId);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	@Test
	public void driverDeleteHint() {

		final Object testingData[][] = {
			// El Administrator logueado todo correcto. -> true
			{
				"admin", 161, 98, null
			}, {
				"admin", 1581111, 98, IllegalArgumentException.class
			}, {
				"admin", 161, 95111, IllegalArgumentException.class
			}, {
				"admin", 161, 98, IllegalArgumentException.class
			}, {
				null, 1581111, 98, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.deleteHintTest((String) testingData[i][0], (int) testingData[i][1], (int) testingData[i][2], (Class<?>) testingData[i][3]);
	}

}
