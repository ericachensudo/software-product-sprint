package com.google.sps.servlets;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.cloud.datastore.Entity;

@WebServlet("/contact-handler")
public class ContactHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String textValue = Jsoup.clean(request.getParameter("text-input"), Safelist.none());

    // Get and sanitize the values entered in the form.
    String name = Jsoup.clean(request.getParameter("name-input"), Safelist.none());
    String email = Jsoup.clean(request.getParameter("email-input"), Safelist.none());
    long timestamp = System.currentTimeMillis();

    // package contact info into "Contact" entity
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    // KeyFactory to create unique IDs for entities
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Contact");
    Key contactKey = datastore.allocateId(keyFactory.newKey());
    FullEntity contactEntity = Entity.newBuilder(contactKey)
        .set("name", name)
        .set("email", email)
        .set("message", textValue)
        .set("timestamp", timestamp)
        .build();
    // store "Contact" in Datastore
    datastore.put(contactEntity);

    // load contact just stored in Datastore
    Query<Entity> query = Query.newEntityQueryBuilder().setKind("Contact")
        .setFilter(PropertyFilter.eq("__key__", contactKey)).build();
    QueryResults<Entity> results = datastore.run(query);

    // extract name from contact entity
    Entity entity = results.next();
    String entityName = entity.getString("name");

    // redirect to "Thanks" page with name as search param
    response.sendRedirect("/thanks.html?name=" + 
    URLEncoder.encode(entityName, StandardCharsets.UTF_8));

  }
}