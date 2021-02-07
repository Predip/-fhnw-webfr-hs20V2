package ch.fhnw.webfr.flashcard.web;

import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
    private static final Log logger = LogFactory.getLog(HelloWorldController.class);

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @GetMapping
    public void myGreeting(@RequestParam(value = "name", required = false) String name, HttpServletResponse response, HttpServletRequest request) throws IOException {
        if (name == null || name.isEmpty()) name = "World";

        long numberOfQuestion = questionnaireRepository.count();
        PrintWriter writer = response.getWriter();
        writer.append("<html><head><title>Flashcard II</title></head><body>");
        writer.append("<span>Hello ").append(name).append("</span><br>");
        writer.append("<span>You have ").append(String.valueOf(numberOfQuestion)).append(" Questionnaires in your Repo</span>");
        writer.append("</body></html>");
    }
}
