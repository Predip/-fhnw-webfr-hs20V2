package ch.fhnw.webfr.flashcard.web;

import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
    private static final Log logger = LogFactory.getLog(HelloWorldController.class);

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @GetMapping
    public @ResponseBody String myGreeting(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isEmpty()) name = "World";
        logger.info("name: "+name);
        logger.info("Questionnaires: "+questionnaireRepository.count());
        return "<html><head><title>Flashcard II</title></head><body>"
                + "<span>Hello " + name + "</span><br>"
                + "<span>You have " + questionnaireRepository.count() + " Questionnaires in your Repo</span>"
                + "</body></html>";
    }
}
