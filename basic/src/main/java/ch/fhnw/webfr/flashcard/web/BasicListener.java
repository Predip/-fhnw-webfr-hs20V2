package ch.fhnw.webfr.flashcard.web;

import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import ch.fhnw.webfr.flashcard.util.QuestionnaireInitializer;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;


@WebListener
public class BasicListener implements ServletContextListener {
    final Logger logger = Logger.getLogger(BasicListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String mode = sce.getServletContext().getInitParameter("mode");
        QuestionnaireRepository repo;

        logger.info("mode: "+mode);

        if ((mode != null) && (mode.equals("test"))) {
            repo = new QuestionnaireInitializer().initRepoWithTestData();
            logger.info("new Repo");
        } else {
            repo = new QuestionnaireRepository();
            logger.info("Repo existing");
        }
        sce.getServletContext().setAttribute("questionnaireRepository", repo);
        logger.info("mode: "+mode);
    }
}
