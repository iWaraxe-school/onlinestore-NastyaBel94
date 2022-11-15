package by.issoft.server;

import by.issoft.domain.Categories;;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;


public class CategoriesHTTPHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("List of Categories");
        DBHelper db = new DBHelper();
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        for (Categories categories : db.getCategories()) {
            array.put(categories.getName());
        }
        json.put("categories", array);
        byte[] bytes = json.toString().getBytes();
        //Headers headers = exchange.getResponseHeaders();
        //headers.add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();

    }
}
