// package com.google.sps.servlets;

// import java.io.IOException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import com.google.gson.Gson;
// import java.util.ArrayList;
// import java.util.List;
// import com.google.cloud.datastore.Datastore;
// import com.google.cloud.datastore.DatastoreOptions;
// import com.google.cloud.datastore.Entity;
// import com.google.cloud.datastore.FullEntity;
// import com.google.cloud.datastore.Key;
// import com.google.cloud.datastore.KeyFactory;
// import com.google.cloud.datastore.Query;
// import com.google.cloud.datastore.QueryResults;
// import com.google.cloud.datastore.StructuredQuery.OrderBy;

// @WebServlet("/forms.html")
// public class ContactServlet extends HttpServlet {

//     // doPost - requests values sent from user, prints them in server logs, and stores them in datastore
//     @Override
//     public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         // Get the value entered in the form.
//         String textValue = request.getParameter("text-input");
//         long timestamp = System.currentTimeMillis();

//         // Print user values in server logs
//         System.out.println("User submitted: " + textValue);
//         System.out.println(timestamp);

//         // Store values in datastore
//         Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
//         KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");
//         FullEntity messageEntity = Entity.newBuilder(keyFactory.newKey())
//                 .set("textValue", textValue)
//                 .set("timestamp", timestamp)
//                 .build();
//         datastore.put(messageEntity);
//         response.sendRedirect("http://www.google.com");  

//         // response.sendRedirect("/week2.html");
//     }

//     // doGet - gets user values stored in datastore to create array of Message objects, sends it back to client-side
//     @Override
//     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
//         Query<Entity> query = Query.newEntityQueryBuilder().setKind("Message").setOrderBy(OrderBy.desc("timestamp"))
//                 .build();
//         QueryResults<Entity> results = datastore.run(query);

//         List<Messages> messagesArray = new ArrayList<>();
//         while (results.hasNext()) {
//             Entity entity = results.next();

//             long id = entity.getKey().getId();
//             String textValue = entity.getString("textValue");
//             long timestamp = entity.getLong("timestamp");

//             Messages oneMessage = new Messages(id, textValue, timestamp);
//             messagesArray.add(oneMessage);
//         }
//         Gson gson = new Gson();
//         response.setContentType("application/json;");
//         response.getWriter().println(gson.toJson(messagesArray));
//     }

//     // doPut - deletes the user's message
//     @Override
//     public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         long id = Long.parseLong(request.getParameter("id"));

//         Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
//         KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");
//         Key messageEntityKey = keyFactory.newKey(id);
//         datastore.delete(messageEntityKey);
//     }
// }