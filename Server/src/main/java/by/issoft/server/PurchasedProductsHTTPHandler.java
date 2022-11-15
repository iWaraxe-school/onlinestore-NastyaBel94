package by.issoft.server;

import by.issoft.domain.Product;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;


public class PurchasedProductsHTTPHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        DBHelper db = new DBHelper();
        JSONObject rootObject = new JSONObject();
        JSONArray array = new JSONArray();
        String userId = HTTPHelper.queryToMap(exchange.getRequestURI().getQuery()).get("userid");

        try { Integer integer = Integer.parseInt(userId);
            for (Product product : db.getPurchasedProducts(integer)) {
                JSONObject purchasedProductsJson = new JSONObject();
                purchasedProductsJson.put("name", product.getName());
                purchasedProductsJson.put("rate", product.getRate());
                purchasedProductsJson.put("price", product.getPrice());
            }
            rootObject.put("purchasedProducts",array);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        byte[] bytes = rootObject.toString().getBytes();
        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();


    }


}

