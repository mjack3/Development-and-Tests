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

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Answer;
import domain.Poll;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class PollTest extends AbstractTest {

	@Autowired
	private PollService		pollService;
	@Autowired
	private InstanceService	instanceService;


	// Tests
	// ====================================================

	@Test
	public void browseCataloguePolls() {
		Assert.isTrue(!this.pollService.findPollActivated().isEmpty());

	}

	protected void answerPollTest(final int pollId, final String data, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			final String[] answers = data.substring(1, data.length()).split(",");
			final List<Answer> ansToSave = new LinkedList<Answer>();
			final Poll p = this.pollService.findOne(pollId);
			for (int i = 0; i < answers.length; i++) {
				final Answer a = new Answer();
				a.setSelected(new Integer(answers[i]));
				a.setQuestion(i + 1);
				ansToSave.add(a);
			}

			this.instanceService.save(ansToSave, p, "city", "MALE", "name");

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	@Test
	public void driverAnswerPoll() {

		final Object testingData[][] = {

			{
				98, ",0,0", null
			}, {
				11111, ",0,0", IllegalArgumentException.class
			}, {
				98, null, NullPointerException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.answerPollTest((int) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void editPollTest(final String username, final int pollId, final String text, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			final Poll poll = this.pollService.findOne(pollId);
			poll.setDescription(text);
			this.pollService.saveAF(poll);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	@Test
	public void driverEditPoll() {

		final Object testingData[][] = {

			{
				"poller1", 98, "Descripción editada", null
			},

			{
				"poller1", 123333, "Descripción editada", IllegalArgumentException.class
			}, {
				"poller1", 98, "", ConstraintViolationException.class
			}, {
				"auditor1", 98, "", IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.editPollTest((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void deletePollTest(final String username, final int pollId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			final Poll poll = this.pollService.findOne(pollId);

			this.pollService.delete(poll);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	@Test
	public void driverDeletePoll() {

		final Object testingData[][] = {

			{
				"poller1", 128, null
			},

			{
				"poller1", 123333, IllegalArgumentException.class
			}, {
				"auditor1", 128, IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.deletePollTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
