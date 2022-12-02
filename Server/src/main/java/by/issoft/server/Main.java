package by.issoft.server;


import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        DBHelper dbHelper = new DBHelper();
        dbHelper.createCategoryTable();
        dbHelper.createProductTable();
        dbHelper.fillStore();
        dbHelper.printContentOfStore();
        dbHelper.printContentOfStoreJoin();
        dbHelper.createUsersTable();
        dbHelper.createCartTable();


        HttpServer server = HttpServer.create();

        server.bind(new InetSocketAddress(9002), 0);
        server.createContext("/getCategories", new CategoriesHTTPHandler());
        server.createContext("/getProducts", new ProductsHTTPHandler());
        server.createContext("/addToCart", new AddToCartHTTPHandler()).setAuthenticator(new Authentication("cart", Charset.defaultCharset()));
        server.createContext("/getPurchasedProducts", new PurchasedProductsHTTPHandler()).setAuthenticator(new Authentication("cart", Charset.defaultCharset()));

        server.setExecutor(null);
        server.start();

        while (true) {
        }
    }

}
