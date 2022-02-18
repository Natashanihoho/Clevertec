package by.gordievich.task.web;

import by.gordievich.task.exceptions.NotEnoughProductsException;
import by.gordievich.task.exceptions.UnknownIdException;
import by.gordievich.task.service.Interpreter;
import by.gordievich.task.service.InterpreterImpl;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.URI;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static by.gordievich.task.web.ConstantHttp.HttpMethod.GET;
import static by.gordievich.task.web.ConstantHttp.HttpResponseStatus.STATUS_NOT_FOUND;
import static by.gordievich.task.web.ConstantHttp.HttpResponseStatus.STATUS_OK;
import static by.gordievich.task.web.ConstantHttp.UrlPath.CHECK_PATH;

public class Controller {

    static final String REG_ATTRIBUTES_DELIMITER = "&";
    static final String REG_ATTRIBUTE_DELIMITER = "=";

    public void execute(final HttpExchange httpExchange) throws IOException, UnknownIdException, NotEnoughProductsException {
        String requestMethod = httpExchange.getRequestMethod();
        String requestType = httpExchange.getRequestURI().getPath();
        URI requestURI = httpExchange.getRequestURI();
        Map<String, String> attributes = new HashMap<>();
        Interpreter interpreter = new InterpreterImpl();

        if(GET.equals(requestMethod) && requestType.matches(CHECK_PATH)) {
            String request = parseRequest(requestURI.getQuery());
            String receipt = interpreter.interpret(request.split(" "));
            writeResponse(httpExchange, receipt, STATUS_OK);
        } else {
            notSupportRequest(httpExchange);
        }

    }

    private String parseRequest(String query) {
        return query.replaceAll("id=", "")
                .replaceAll("&value=", "-")
                .replaceAll("&", " ");
    }

    private void writeResponse(final HttpExchange httpExchange,String response, int status) throws IOException {
        Headers responseHeaders = httpExchange.getResponseHeaders();
        StringBuilder db = new StringBuilder();
        db.append(response);
        responseHeaders.add("Content-type", "text/*");
        httpExchange.sendResponseHeaders(status,  db.toString().getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream responseBody = httpExchange.getResponseBody()) {
            responseBody.write(db.toString().getBytes(StandardCharsets.UTF_8));
            responseBody.flush();
        }
    }

    private void notSupportRequest(final HttpExchange httpExchange) {
        try {
            httpExchange.sendResponseHeaders(STATUS_NOT_FOUND, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
