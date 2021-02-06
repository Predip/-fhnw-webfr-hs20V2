package ch.fhnw.webfr.flashcard.web;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Set;

@WebFilter(urlPatterns = { "/*" })
public class TranslationFilter implements Filter {

    private final Logger logger = Logger.getLogger(TranslationFilter.class);
    private Properties translationProperty;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
        PrintWriter responseWriter = response.getWriter();

        chain.doFilter(request, responseWrapper);

        if (responseWrapper.getContentType().contains("text/html")) {
            String originalContent = responseWrapper.toString();
            String newContent = originalContent;
            Set<Object> transKey = translationProperty.keySet();
            for (Object key : transKey) newContent = originalContent
                    .replace(key.toString(), translationProperty.get(key).toString());

            response.setContentLength(newContent.length());
            responseWriter.write(newContent);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String filename = filterConfig.getServletContext().getInitParameter("i18n");
        if (filename == null)  throw new Error("File not found");

        translationProperty = new Properties();
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            translationProperty.load(new InputStreamReader(is, StandardCharsets.UTF_8));
        } catch (IOException e) { throw new ServletException(e.getMessage()); }

        logger.debug("TranslationFilter init() called");
    }
}
