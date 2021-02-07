package ch.fhnw.webfr.flashcard.web;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/questionnaires")
public class QuestionnaireController {
	private static final Log logger = LogFactory.getLog(QuestionnaireController.class);

	@Autowired
	private QuestionnaireRepository questionnaireRepository;
	private static final String PAGETITLE = "<html><head><title>Flashcard SpringMVC</title></head><body>";
	private static final String PAGEEND = "</body></html>";
	private PrintWriter writer;

	@GetMapping
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Questionnaire> questionnaires = questionnaireRepository.findAll();
		setWriter(response);
		writer.append("<h3>Frageb&ouml;gen</h3>");
		for (Questionnaire questionnaire : questionnaires) {
			String url = request.getContextPath() + request.getServletPath();
			if (!url.endsWith("/")) url += "/";
			url = response.encodeURL(url + questionnaire.getId());
			writer.append("<p><a href='").append(url).append("'>").append(questionnaire.getTitle()).append("</a></p>");
		}
		writer.append(PAGEEND);
		logger.debug("Found " + questionnaires.size() + " questionnaires");
	}

	@GetMapping("/{id}")
	public void findById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Questionnaire question = questionnaireRepository.findById(id);
		setWriter(response);
		writer.append("<h3>Question</h3>");
		if (question != null) {
			writer.append("<h4>").append(question.getTitle()).append("</h4>");
			writer.append("<p>").append(question.getDescription()).append("</p>");
			logger.debug("Found questionnaire with id=" + id);
		} else {
			writer.append("<p>no questions found</p>");
			logger.warn("Could not find questionnaire with id=" + id);
		}
		writer.append(PAGEEND);
	}

	public void setWriter(HttpServletResponse response) throws IOException {
		this.writer = response.getWriter();
		writer.append(PAGETITLE);
	}
}
