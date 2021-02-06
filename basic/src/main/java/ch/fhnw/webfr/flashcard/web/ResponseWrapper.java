package ch.fhnw.webfr.flashcard.web;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.PrintWriter;

public class ResponseWrapper extends HttpServletResponseWrapper {
    private CharArrayWriter writer;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        writer =  new CharArrayWriter();
    }

    public PrintWriter getWriter() {
        return new PrintWriter(writer);
    }

    public String toString() {
        return writer.toString();
    }
}
