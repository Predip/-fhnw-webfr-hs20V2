package ch.fhnw.webfr.flashcard.persistence;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionnaireRepository extends MongoRepository<Questionnaire, String> {
	List<Questionnaire> findByTitle(String title);
}
