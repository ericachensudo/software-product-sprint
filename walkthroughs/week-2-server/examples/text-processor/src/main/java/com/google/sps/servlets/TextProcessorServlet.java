package com.google.sps.servlets;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that processes text. */
@WebServlet("/text")
public final class TextProcessorServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String text = getParameter(request, "text-input", "");
    boolean upperCase = Boolean.parseBoolean(getParameter(request, "upper-case", "false"));
    boolean sort = Boolean.parseBoolean(getParameter(request, "sort", "false"));

    // Convert the text to upper case.
    if (upperCase) {
      text = text.toUpperCase();
    }

    // Break the text into individual words.
    String[] words = text.split("\\s*,\\s*");

    // Sort the words.
    if (sort) {
      Arrays.sort(words);
    }

    // Respond with the result.
    response.setContentType("text/html;");
    response.getWriter().println(Arrays.toString(words));
    response.sendRedirect("https://google.com");
  }

  /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}
