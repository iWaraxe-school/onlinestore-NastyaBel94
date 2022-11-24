package by.issoft.server;

import by.issoft.domain.Product;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class AddToCartHTTPHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        DBHelper db = new DBHelper();
        String userId = HTTPHelper.queryToMap(exchange.getRequestURI().getQuery()).get("userid");
        String productId = HTTPHelper.queryToMap(exchange.getRequestURI().getQuery()).get("productid");
        db.addToCart(userId, productId);
        byte[] bytes = productId.getBytes();
        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();


        /*String text = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        JSONObject rootObject = new JSONObject(text);

        String userId = rootObject.getString("userid");
        String productId = rootObject.getString("productid");
        db.addToCart(userId, productId);

        exchange.sendResponseHeaders(200, 0);*/

    }
}
