package com.google.sps.servlets;

import java.io.IOException;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;

// Servlet that responds with JSON array
@WebServlet("/json")
public class JsonServlet extends HttpServlet {

    private static final ArrayList<String> jsonArray = new ArrayList<>(Arrays.asList(
        "This is what JSON looks like", "Testing", "ABC123"));
    private static final Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Convert ArrayList type to JSON
        response.setContentType("application/json;");

        // Convert to JSON using Gson
        String json = gson.toJson(jsonArray);

        // Send the JSON as the response
        response.getWriter().println(json);
    }
}