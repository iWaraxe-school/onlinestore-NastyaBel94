package by.issoft.store;


import by.issoft.domain.Categories;
import by.issoft.domain.CategoryFactory;
import by.issoft.domain.CategoryType;
import by.issoft.domain.Product;
import by.issoft.domain.categories.AnimalCategory;
import by.issoft.domain.categories.ArtistCategory;
import by.issoft.domain.categories.BookCategory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow;

import static org.reflections.util.ConfigurationBuilder.build;


public class MyClient {
    private String serverAddress;
    private int userId;

    public MyClient (String serverAddress){
        while(serverAddress.endsWith("/")){
            serverAddress = serverAddress.substring(0,serverAddress.length()-1);
        }
        this.serverAddress = serverAddress;
    }

    public void setUserId(int userId){
     this.userId = userId;
    }

    public List<Categories> getCategories() throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newBuilder()
                .build();
        HttpRequest request = HttpRequest.newBuilder(new URI(serverAddress + "/getCategories"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Categories> listOfCategories = new ArrayList<>();
        JSONObject json = new JSONObject(response.body());
        JSONArray array = json.getJSONArray("categories");
        CategoryFactory factory = new CategoryFactory();
        for (Object item : array) {
            if (Objects.equals("Animal", item)) {
                listOfCategories.add(new AnimalCategory());
            } else if (Objects.equals("Artist", item)) {
                listOfCategories.add(new ArtistCategory());
            } else if (Objects.equals("Book", item)) {
                listOfCategories.add(new BookCategory());
            }
        }
        return listOfCategories;

    }

    public List<Product> getProducts() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .build();
        HttpRequest request = HttpRequest.newBuilder(new URI(serverAddress + "/getProducts"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Product> listOfProducts = new ArrayList<>();
        JSONObject rootObject = new JSONObject(response.body());
        JSONArray array = rootObject.getJSONArray("products");
        //List<Categories> listOfCategories = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject productJson = array.getJSONObject(i);
            Product product = new Product();
            product.setName(productJson.getString("name"));
            product.setRate(productJson.getInt("rate"));
            product.setPrice(productJson.getInt("price"));
            listOfProducts.add(product);
        }
        return listOfProducts;
    }

    public void addToCart(int productid) throws URISyntaxException, IOException, InterruptedException {
        JSONObject obj = new JSONObject();
        obj.put("userid", userId);
        obj.put("productid", productid);
        String data = obj.toString();
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder(new URI(serverAddress + "/addToCart"))
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();
        client.send(request,HttpResponse.BodyHandlers.discarding());


    }

    public List<Product> getPurchasedProducts() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .build();
        HttpRequest request = HttpRequest.newBuilder(new URI(serverAddress + "/getPurchasedProducts?userid="+userId))
                .GET()
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Product> listOfPurchasedProducts = new ArrayList<>();
        JSONObject rootObject = new JSONObject(response.body());
        JSONArray array = rootObject.getJSONArray("purchasedProducts");
        for (int i = 0; i < array.length(); i++) {
            JSONObject purchasedProductsJson = array.getJSONObject(i);
            Product purchasedProduct = new Product();
            purchasedProduct.setName(purchasedProductsJson.getString("name"));
            purchasedProduct.setRate(purchasedProductsJson.getInt("rate"));
            purchasedProduct.setPrice(purchasedProductsJson.getInt("price"));
            listOfPurchasedProducts.add(purchasedProduct);
        }
        return listOfPurchasedProducts;
    }
}
