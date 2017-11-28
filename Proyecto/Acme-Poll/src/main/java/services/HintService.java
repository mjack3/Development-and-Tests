
package services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HintRepository;
import security.LoginService;
import domain.Hint;
import domain.Poll;

@Service
@Transactional
public class HintService {

	@Autowired
	private HintRepository	hintRepository;

	@Autowired
	private PollService		pollService;


	public HintService() {
		super();
	}

	public void delete(final Integer arg0) {
		Assert.notNull(arg0);

		this.hintRepository.delete(arg0);
	}

	public List<Hint> findAll() {
		return this.hintRepository.findAll();
	}

	public Hint findOne(final Integer arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.hintRepository.exists(arg0));
		return this.hintRepository.findOne(arg0);
	}

	public Hint save(Hint arg0, final Integer q) {
		Assert.notNull(arg0);

		final Poll p = this.pollService.findOne(q);

		final List<Hint> list = (List<Hint>) p.getHints();
		arg0 = this.hintRepository.save(arg0);
		list.add(arg0);

		p.setHints(list);

		this.pollService.update(p);

		return arg0;
	}

	public Hint update(final Hint arg0) {
		Assert.notNull(arg0);
		Assert.isTrue(this.hintRepository.exists(arg0.getId()));
		return this.hintRepository.save(arg0);
	}

	public Hint create() {
		final Hint hint = new Hint();

		hint.setMark(new Double(0.0));
		hint.setMarks(new ArrayList<Integer>());
		hint.setText("");

		return hint;
	}

	public Hint score(final Integer q, final Integer t) {
		Assert.isTrue(this.hintRepository.exists(q));
		final Hint h = this.hintRepository.findOne(q);

		final List<Integer> list = h.getMarks();
		list.add(t);
		h.setMarks(list);

		Double mark = 0.0;
		Integer ac = 0;
		for (final Integer i : h.getMarks())
			ac += i;
		mark = (double) (ac / h.getMarks().size());
		h.setMark(mark);

		return this.hintRepository.save(h);

	}

	public void remove(final Integer q, final Integer p) {
		Assert.isTrue(LoginService.hasRole("ADMINISTRATOR"));
		Assert.isTrue(this.hintRepository.exists(q));
		final Hint h = this.hintRepository.findOne(q);
		final Poll poll = this.pollService.findOne(p);

		final List<Hint> list = (List<Hint>) poll.getHints();
		list.remove(h);
		poll.setHints(list);

		this.pollService.update(poll);

		this.hintRepository.delete(h);

	}
}
