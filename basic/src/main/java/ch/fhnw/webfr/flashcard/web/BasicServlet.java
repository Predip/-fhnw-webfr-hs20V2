package ch.fhnw.webfr.flashcard.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/app/*"})
public class BasicServlet extends HttpServlet {
	/*
	 * Attention: This repository will be used by all clients, concurrency
	 * could be a problem. THIS VERSION IS NOT PRODUCTION READY!
	 */
	private QuestionnaireRepository questionnaireRepository;
	private static final String PAGETITLE = "<html><head><title>Example</title></head><body>";
	private static final String PAGEEND = "</body></html>";
	private PrintWriter writer;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		String[] pathElements = request.getRequestURI().split("/");

		try {
			setWriter(response);
			writer.append(PAGETITLE);
			if (isLastPathElementQuestionnaires(pathElements)) handleQuestionnairesRequest(request, response);
			else if (isLastPathElementQuestions(pathElements)) handleQuestionsRequest(pathElements[pathElements.length - 1]);
			else handleIndexRequest(request, response);
			writer.append(PAGEEND);
		} catch (IOException e) { System.err.println("IO Exception: "+e); }
	}

	private boolean isLastPathElementQuestionnaires(String[] pathElements) {
		String last = pathElements[pathElements.length - 1];
		return last.equals("questionnaires");
	}

	private boolean isLastPathElementQuestions(String[] pathElements) {
		String questionPath = pathElements[pathElements.length - 2];
		if (questionPath.equals("questionnaires")) {
			String last = pathElements[pathElements.length - 1];
			if (last == null) return false;
			try {
				List<Questionnaire> questionnaires = questionnaireRepository.findAll();
				Long id = Long.parseLong(last);
				return id <= questionnaires.size() && id >= 0;
			} catch (NullPointerException | NumberFormatException nfe) { return false; }
		}
		return false;
	}

	private void handleQuestionnairesRequest(HttpServletRequest request, HttpServletResponse response) {
		List<Questionnaire> questionnaires = questionnaireRepository.findAll();
		writer.append("<h3>Frageb&ouml;gen</h3>");
		for (Questionnaire questionnaire : questionnaires) {
			String url = request.getContextPath() + request.getServletPath();
			url = url + "/questionnaires/" + questionnaire.getId().toString();
			writer.append("<p><a href='" + response.encodeURL(url) + "'>" + questionnaire.getTitle() + "</a></p>");
		}
	}

	private void handleQuestionsRequest(String pathElement) {
		Questionnaire question = questionnaireRepository.findById(Long.parseLong(pathElement));
		writer.append("<h3>Question</h3>");
		if (question != null) {
			writer.append("<h4>"+question.getTitle()+"</h4>");
			writer.append("<p>"+question.getDescription()+"</p>");
		} else writer.append("<p>no questions found</p>");
	}

	private void handleIndexRequest(HttpServletRequest request, HttpServletResponse response) {
		writer.append("<h3>Welcome</h3>");
		String url = request.getContextPath() + request.getServletPath();
		writer.append("<p><a href='" + response.encodeURL(url) + "/questionnaires'>All Questionnaires</a></p>");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		questionnaireRepository = (QuestionnaireRepository) config.getServletContext()
				.getAttribute("questionnaireRepository");
	}

	public PrintWriter getWriter() { return writer; }

	public void setWriter(HttpServletResponse response) throws IOException {
		this.writer = response.getWriter();
	}

}
