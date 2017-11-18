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
	private PollService	pollService;
	@Autowired
	private InstanceService instanceService;
	
	// Tests
		// ====================================================

		
	
	
	@Test
	public void browseCataloguePolls() {
		Assert.isTrue(!pollService.findPollActivated().isEmpty());
		
	}
	
	protected void answerPollTest(int pollId,String data, Class<?> expected) {
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
			
			

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);

	}
	
	@Test
	public void driverAnswerPoll() {

		Object testingData[][] = {
			
			{
				144,",0,0", null
			},{
				11111,",0,0", ConstraintViolationException.class
			},{
				144,null, NullPointerException.class
			}			
		};

		for (int i = 0; i < testingData.length; i++) {
			answerPollTest((int) testingData[i][0],(String) testingData[i][1],(Class<?>) testingData[i][2]);
		}
	}
	
	
	protected void editPollTest(String username, int pollId,String text,Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			authenticate(username);
			Poll poll = pollService.findOne(pollId);
			poll.setDescription(text);
			this.pollService.saveAF(poll);
			
			unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);

	}
	
	@Test
	public void driverEditPoll() {

		Object testingData[][] = {
			
			{
				"poller1",95,"Descripción editada", null
			},
			
			{
				"poller1",123333,"Descripción editada", NullPointerException.class
			},
			{
				"poller1",95,"", ConstraintViolationException.class
			},
			{
				"auditor1",95,"", IllegalArgumentException.class
			}
			
			
		};

		for (int i = 0; i < testingData.length; i++) {
			editPollTest((String) testingData[i][0],(int) testingData[i][1],(String) testingData[i][2],(Class<?>) testingData[i][3]);
		}
	}
	
	protected void deletePollTest(String username, int pollId,Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			authenticate(username);
			Poll poll = pollService.findOne(pollId);
			
			this.pollService.delete(poll);
			
			unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);

	}
	
	@Test
	public void driverDeletePoll() {

		Object testingData[][] = {
			
			{
				"poller1",95, null
			},
			
			{
				"poller1",123333, IllegalArgumentException.class
			},
			{
				"auditor1",95, IllegalArgumentException.class
			}
			
			
		};

		for (int i = 0; i < testingData.length; i++) {
			deletePollTest((String) testingData[i][0],(int) testingData[i][1],(Class<?>) testingData[i][2]);
		}
	}
	
	
	
	
	
	
	

}
