package ch.fhnw.webfr.flashcard.web;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

	@DeleteMapping("/{id}")
	public String delete(@PathVariable String id) {
		Optional<Questionnaire> question = questionnaireRepository.findById(id);
		if (question.isPresent()) {
			questionnaireRepository.deleteById(id);
			return "redirect:/questionnaires";
		} else {
			logger.warn("Could not find questionnaire with id=" + id);
			return "404";
		}
	}

	@GetMapping(value = "/{id}", params = "form")
	public String updateForm(@PathVariable String id, Model model) {
		Optional<Questionnaire> question = questionnaireRepository.findById(id);
		if (question.isPresent()) {
			model.addAttribute("questionnaire", question.get());
			return "questionnaires/update";
		} else {
			logger.warn("Could not find questionnaire with id=" + id);
			return "404";
		}
	}

	@PutMapping("/{id}")
	public String update(@PathVariable String id, @Valid Questionnaire questionnaire,
						 BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
			logger.debug("Errors: " + bindingResult.getAllErrors());
			return "questionnaires/update";
		}

		Optional<Questionnaire> question = questionnaireRepository.findById(id);
		if (question.isPresent()) {
			Questionnaire oldQ = question.get();
			oldQ.setDescription(questionnaire.getDescription());
			oldQ.setTitle(questionnaire.getTitle());
			logger.debug("update" + oldQ);
			questionnaireRepository.save(oldQ);
		}
		return "redirect:/questionnaires";
	}
}
