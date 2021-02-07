package ch.fhnw.webfr.flashcard.web;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/questionnaires")
public class QuestionnaireController {
	private static final Log logger = LogFactory.getLog(QuestionnaireController.class);

	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	@GetMapping
	public String findAll(Model model) {
		List<Questionnaire> questionnaires = questionnaireRepository.findAll();
		logger.debug("Found " + questionnaires.size() + " questionnaires");
		model.addAttribute("questionnaires", questionnaires);
		return "questionnaires/list";
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable String id, Model model) {
		Optional<Questionnaire> question = questionnaireRepository.findById(id);
		if (question.isPresent()) {
			logger.debug("Found questionnaire with id=" + id);
			model.addAttribute("questionnaire", question.get());
			return "questionnaires/show";
		} else {
			logger.warn("Could not find questionnaire with id=" + id);
			return "404";
		}
	}

	@GetMapping(params = "form")
	public String getForm(Model model) {
		Questionnaire questionnaire = new Questionnaire();
		logger.debug("open Form with new " + questionnaire);
		model.addAttribute("questionnaire", questionnaire);
		return "questionnaires/create";
	}

	@PostMapping
	public String create(@Valid Questionnaire questionnaire, BindingResult bindingResult){
		if (bindingResult.hasErrors()) return "questionnaires/create";
		logger.debug("new" + questionnaire);
		questionnaireRepository.save(questionnaire);
		return "redirect:/questionnaires";
	}
}
