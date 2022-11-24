package by.issoft.server;


import by.issoft.domain.Product;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class ProductsHTTPHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        DBHelper db = new DBHelper();
        JSONObject rootObject = new JSONObject();
        JSONArray array = new JSONArray();
        for (Product product : db.getProducts()) {
            JSONObject productJson = new JSONObject();
            productJson.put("name", product.getName());
            productJson.put("rate", product.getRate());
            productJson.put("price", product.getPrice());
            array.put(productJson);
        }
        rootObject.put("products",array);



        byte[] bytes = rootObject.toString().getBytes();
        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();

    }
}
