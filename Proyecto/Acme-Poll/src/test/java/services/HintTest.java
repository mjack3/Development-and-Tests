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

	
	protected void writeHintTest(String username ,String text, int pollId, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			
			
			authenticate(username);
			Hint hint = hintService.create();
			hint.setText(text);
			hintService.save(hint, pollId);
			unauthenticate();
			
			

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);

	}
	
	@Test
	public void driverWriteHint() {

		Object testingData[][] = {
			
			{
				"poller1","texto del hint",95, null
			},
			{
				"poller1","",95, ConstraintViolationException.class
			},
			{
				"poller1","texto del hint",951111, NullPointerException.class
			},
			{
				null,"texto del hint",95, ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			writeHintTest((String) testingData[i][0],(String) testingData[i][1],(int) testingData[i][2],(Class<?>) testingData[i][3]);
		}
	}
	protected void scoreHintTest(String username ,int hintId, int score, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			
			
			authenticate(username);
			hintService.score(hintId,score);
			unauthenticate();
			
			

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);

	}
	
	@Test
	public void driverScoreHint() {

		Object testingData[][] = {
			
			{
				"poller1",158,9, null
			},
			{
				"poller1",1581111,9, NullPointerException.class
			},
			{
				null,158,9, null
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			scoreHintTest((String) testingData[i][0],(int) testingData[i][1],(int) testingData[i][2],(Class<?>) testingData[i][3]);
		}
	}
	
	protected void deleteHintTest(String username,int hintId,int pollId, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			authenticate(username);
			
			hintService.remove(hintId, pollId);
			unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);

	}
	
	@Test
	public void driverDeleteHint() {

		Object testingData[][] = {
			// El Administrator logueado todo correcto. -> true
			{
				"admin",158, 95, null
			},
			{
				"admin",1581111, 95, IllegalArgumentException.class
			},
			{
				"admin",158, 95111, IllegalArgumentException.class
			},
			{
				"admin",158, 115, IllegalArgumentException.class
			},
			{
				null,1581111, 95, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++) {
			deleteHintTest((String) testingData[i][0], (int) testingData[i][1],(int) testingData[i][2],(Class<?>) testingData[i][3]);
		}
	}
	
	
	
	
	
	
	
	

}
