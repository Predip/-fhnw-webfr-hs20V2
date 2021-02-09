package ch.fhnw.webfr.flashcard.web;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/questionnaires")
public class QuestionnaireController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	@GetMapping
	public ResponseEntity<List<Questionnaire>> findAll() {
		Sort sort = Sort.by(Sort.Direction.ASC, "id");
		List<Questionnaire> questionnaires = questionnaireRepository.findAll(sort);
		logger.debug("Found " + questionnaires.size() + " questionnaires");
		return new ResponseEntity<>(questionnaires, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Questionnaire> findById(@PathVariable String id) {
		Optional<Questionnaire> question = questionnaireRepository.findById(id);
		if (question.isPresent()) {
			logger.debug("Found questionnaire with id=" + id);
			return new ResponseEntity<>(question.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Questionnaire> create(@Valid @RequestBody Questionnaire questionnaire, BindingResult bindingResult){
		if (bindingResult.hasErrors()) return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		questionnaire = questionnaireRepository.save(questionnaire);
		logger.debug("Created questionnaire with id=" + questionnaire.getId());
		return new ResponseEntity<>(questionnaire, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		Optional<Questionnaire> question = questionnaireRepository.findById(id);
		if (question.isPresent()) {
			questionnaireRepository.deleteById(id);
			logger.debug("Deleted questionnaire with id=" + id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		logger.debug("Could not find questionnaire with id=" + id);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Questionnaire> update(@PathVariable String id, @Valid @RequestBody Questionnaire questionnaire,
						 BindingResult bindingResult){
		if (bindingResult.hasErrors()) return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		Optional<Questionnaire> question = questionnaireRepository.findById(id);
		if (question.isPresent()) {
			Questionnaire oldQ = question.get();
			if (questionnaire.getDescription() != null) oldQ.setDescription(questionnaire.getDescription());
			if (questionnaire.getTitle() != null) oldQ.setTitle(questionnaire.getTitle());
			questionnaireRepository.save(oldQ);
			logger.debug("Updated questionnaire with id=" + oldQ.getId());
			return new ResponseEntity<>(oldQ, HttpStatus.OK);
		}
		logger.debug("No questionnaire with id=" + id + " found");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}