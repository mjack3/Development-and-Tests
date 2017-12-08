
package controllers;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.InstanceService;
import services.PollService;
import domain.Answer;
import domain.Poll;

@Controller
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	private PollService		pollService;

	@Autowired
	private InstanceService	instanceService;

	@Autowired
	private ActorService	actorService;

	private Integer			toSave;


	@RequestMapping("/answer")
	public ModelAndView answer(@RequestParam final Integer q) {
		ModelAndView res = new ModelAndView("answer/answer");

		/*
		 * Se a�ade la cabeceras @coockieValue y httpResponde en el m�todo que usar� coockies.
		 * Las cookes se almacenan bajo la cadena id1/id2/..
		 * 
		 * La variable a tratar es, en este caso hitCounter, que es una cadena b�sica donde se anidan
		 * las encuestas visitadas.
		 * 
		 * 
		 * � SOLUCI�N PARA EL SISTEMA DE ENCUESTAS !
		 * 
		 * Romper la cadena hiitCounter en un String partiendo por el par�metro '/'
		 * Si la idPoll que recibe el m�todo est� contenido dentro de esta string, lanzar
		 * una excepci�n.
		 * 
		 * En caso contrario, almacenarla en la coockie y proseguir al guardado
		 * 
		 * En las l�neas siguientes est� todo el sistema de coockies implementado
		 */

		try {
			final Poll poll = this.pollService.findOne(q);
			this.toSave = q;

			res.addObject("question", poll.getQuestions());
		} catch (final Throwable e) {
			res = new ModelAndView("redirect:/welcome/index.do");
		}

		return res;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(final String data, final String gender, final String city, final String name, @CookieValue(value = "hitCounter", defaultValue = "") String hitCounter, final HttpServletResponse response) {

		String res = null;
		final Poll p = this.pollService.findOne(this.toSave);

		try {

			final String[] answers = data.substring(1, data.length()).split(",");
			final List<Answer> ansToSave = new LinkedList<Answer>();

			for (int i = 0; i < answers.length; i++) {
				final Answer a = new Answer();
				a.setSelected(new Integer(answers[i]));
				a.setQuestion(i + 1);
				ansToSave.add(a);
			}

			/*
			 * Antes del save: Comprobamos si hemos visitado la encuesta
			 */

			final String[] array = hitCounter.split("/");
			for (final String string : array)
				if (string.equals(this.toSave.toString()))
					break;	// hemos encontrado que la poll ha sido respondida y se devolver� null
				else {		// poll no ha sido respondida, se procede a guardar
					hitCounter += this.toSave + "/";
					this.instanceService.save(ansToSave, p, city, gender, name);	// no hemos respondido aun la encuesta
					res = "poll/list";
				}

			// Dos l�neas obligatorias: hay que refrescar la coockie y a�adirla al httpResponseServlet
			final Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
			response.addCookie(cookie);

		} catch (final Exception e) {
			res = "poll/list";
		}
		return res;
	}
}
