package ru.nshi.Task2;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SortServlet extends HttpServlet {
    public static final String JSON_VALUE = "application/json";
    private ObjectMapper objectMapper = new ObjectMapper();
    private JsonParse jsonParse;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(JSON_VALUE);

        if (!req.getContentType().contains(JSON_VALUE) || req.getContentType() == null) {
            resp.setStatus(400);
            objectMapper.writeValue(resp.getWriter(), Map.of("error", "Expected " + JSON_VALUE));
            return;
        }

        SortingRequest sortingRequest = objectMapper.readValue(req.getInputStream(), SortingRequest.class);

        if (sortingRequest.getValues() == null) {
            resp.setStatus(400);
            objectMapper.writeValue(resp.getWriter(), Map.of("error", "Array is null"));
            return;
        }

        String algorithm = req.getParameter("algorithm");
        if (algorithm == null)
            algorithm = "bubble";

        String sortingResponse;
        try {
            jsonParse = new JsonParse();
            sortingResponse = jsonParse.sortAndSerialize(new JsonModel(algorithm, sortingRequest.getValues()));

        } catch (NullPointerException e) {
            objectMapper.writeValue(resp.getWriter(), Map.of("error", "Unsupported algorithm"));
            System.out.println("huy");
            resp.setStatus(404);
            return;
        }

        objectMapper.writeValue(resp.getWriter(), sortingResponse);
    }
}
