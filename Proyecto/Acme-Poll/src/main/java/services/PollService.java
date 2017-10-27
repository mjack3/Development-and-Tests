
package services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PollRepository;
import domain.Poll;

@Service
@Transactional
public class PollService {

	//Manager repositories

	@Autowired
	private PollRepository pollRepository;


	//Constructor

	public PollService() {
		super();
	}

	//CRUD Methods

	public List<Poll> findAll() {
		return this.pollRepository.findAll();
	}

	public Poll findOne(final Integer arg0) {
		Assert.notNull(arg0);
		return this.pollRepository.findOne(arg0);
	}

	public List<Poll> findPollActivated() {
		final Date now = new Date();

		//DateFormat.getDateInstance().format(now);

		//System.out.print(now.toString());

		return this.pollRepository.findPollActivated(now);

	}
	public String findMinAvgStdMaxPollsByPoller(){
		String res = "Min: " + pollRepository.findMinAvgStdMaxPollsByPoller()[0] 
				+ ", Avg: " +  pollRepository.findMinAvgStdMaxPollsByPoller()[1]
						+ ", Std: " +  pollRepository.findMinAvgStdMaxPollsByPoller()[2]
								+ ", Max: " +  pollRepository.findMinAvgStdMaxPollsByPoller()[3];
		return res;
		
	}
	public String findMinAvgStdMaxInstancesByPoll(){
		String res = "Min: " + pollRepository.findMinAvgStdMaxInstancesByPoll()[0] 
				+ ", Avg: " +  pollRepository.findMinAvgStdMaxInstancesByPoll()[1]
						+ ", Std: " +  pollRepository.findMinAvgStdMaxInstancesByPoll()[2]
								+ ", Max: " +  pollRepository.findMinAvgStdMaxInstancesByPoll()[3];
		return res;
	}

}
