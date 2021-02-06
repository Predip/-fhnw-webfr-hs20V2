package ch.fhnw.webfr.flashcard.web;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(urlPatterns = {"/*"})
public class BasicFilter implements Filter {
    final Logger logger = Logger.getLogger(BasicFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURL = httpRequest.getRequestURI();
        logger.debug("Before request [uri=" + requestURL +"]");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        logger.debug("BasicFilter init() called");
    }

}
