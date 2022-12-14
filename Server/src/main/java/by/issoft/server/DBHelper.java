package by.issoft.server;

import by.issoft.domain.Categories;
import by.issoft.domain.CategoryFactory;
import by.issoft.domain.CategoryType;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import com.github.javafaker.Faker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    static Connection CONNECTION = null;
    static ResultSet RESULTSET = null;

    static final String URL = "jdbc:mysql://localhost:3306/mysql";
    static final String USER = "root";
    static final String PASSWORD = "password";
//    Store store;
//
//    public DBHelper(Store store) {
//        this.store = store;
//    }

    public void connectionToDB() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                System.out.println("Connection succesfull!");
            } catch (Exception ex) {
                System.out.println("Connection failed...");

                System.out.println(ex);
            }

            CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("\nConnection to DB is successfully\n");
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void createCategoryTable() {
        connectionToDB();
        try (Statement statement = CONNECTION.createStatement()) {

            String sqlCommand1 = "DROP TABLE IF EXISTS Categories";
            statement.executeUpdate(sqlCommand1);
            String sqlCommand = "CREATE TABLE Categories (" +
                    "Id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "Name VARCHAR(40) NOT NULL ," +
                    "Type INT NOT NULL)";
            statement.execute(sqlCommand);
            System.out.println("Categories table has been created");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void createProductTable() {
        connectionToDB();
        try (Statement statement = CONNECTION.createStatement()) {
            String sqlCommand2 = "DROP TABLE IF EXISTS Products";
            statement.executeUpdate(sqlCommand2);
            String sqlCommand =
                    "CREATE TABLE Products (" +
                            "Id INT NOT NULL AUTO_INCREMENT, " +
                            "Name NVARCHAR(100) NOT NULL, " +
                            "Rate INT NOT NULL, " +
                            "Price INT NOT NULL,  " +
                            "CategoryId INT, " +
                            "PRIMARY KEY (Id)," +
                            "    CONSTRAINT FK_CategoryId FOREIGN KEY (CategoryId)\n" +
                            "    REFERENCES Categories(Id))";
            statement.execute(sqlCommand);
            System.out.println("Products table has been created");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void fillStore() throws SQLException {
        connectionToDB();
        try (Statement statement = CONNECTION.createStatement()) {
            Faker faker = new Faker();
            CategoryType[] categoryType = CategoryType.values();
            CategoryFactory factory = new CategoryFactory();
            for (CategoryType type : categoryType) {

                Categories category = factory.getCategory(type);
                String sqlCommand = "INSERT INTO Categories VALUES (DEFAULT, " +
                        "'" + category.getName() + "'," +
                        type.getValue() + ")";
                statement.executeUpdate(sqlCommand);
                RESULTSET = statement.executeQuery("SELECT LAST_INSERT_ID()");
                RESULTSET.next();
                int categoryId = RESULTSET.getInt(1);
                for (int i = faker.number().numberBetween(1, 10); i > 0; --i) {
                    String productName;
                    switch (category.getName()) {
                        case "Book":
                            productName = faker.book().title();
                            break;
                        case "Animal":
                            productName = faker.animal().name();
                            break;
                        case "Artist":
                            productName = faker.artist().name();
                            break;
                        default:
                            throw new IllegalArgumentException("Category type is invalid");

                    }
                    sqlCommand = "INSERT INTO Products VALUES(" +
                            "DEFAULT," +
                            "'" + productName + "'" + "," +
                            faker.number().numberBetween(0, 10) + "," +
                            faker.number().numberBetween(1, 1000) + "," +
                            categoryId + ")";
                    statement.executeUpdate(sqlCommand);
                }
            }
        }
    }

    public void printContentOfStore() {
        connectionToDB();
        try (Statement statement = CONNECTION.createStatement()) {
            System.out.println("\nPrint store from DB\n");
            RESULTSET = statement.executeQuery("SELECT * FROM Categories ORDER BY Name");
            System.out.println("List of Categories\n");
            while (RESULTSET.next()) {
                System.out.println(RESULTSET.getInt("Id") + "," +
                        RESULTSET.getString("Name"));
            }
            RESULTSET = statement.executeQuery("SELECT * FROM Products ORDER BY Id");
            System.out.println("\nList of Products\n");
            while (RESULTSET.next()) {
                System.out.println(RESULTSET.getInt("Id") + "," +
                        RESULTSET.getString("Name") + "," +
                        RESULTSET.getInt("Rate") + "," +
                        RESULTSET.getInt("Price") + "," +
                        RESULTSET.getInt("CategoryId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void printContentOfStoreJoin() throws SQLException {
        connectionToDB();
        try (Statement statement = CONNECTION.createStatement()) {
            String sql = "SELECT Products.Name, Products.Rate, Products.Price, Categories.Name FROM Products " +
                    "JOIN Categories ON Products.CategoryId = Categories.Id";
            RESULTSET = statement.executeQuery(sql);
            System.out.println("\nList of all Products and Categories\n");
            while (RESULTSET.next()) {
                System.out.println(RESULTSET.getString("Products.Name") + "-" +
                        RESULTSET.getString("Products.Rate") + "-" +
                        RESULTSET.getString("Products.Price") + "-" +
                        RESULTSET.getString("Categories.Name"));
            }
        }
    }

    public List<Categories> getCategories() {
        connectionToDB();
        List<Categories> listOfCategories = new ArrayList<>();
        try (Statement statement = CONNECTION.createStatement()) {
            CategoryFactory categoryFactory = new CategoryFactory();
            RESULTSET = statement.executeQuery("SELECT DISTINCT Name, Type FROM Categories ORDER BY Name");
            while (RESULTSET.next()) {
                listOfCategories.add(categoryFactory.getCategory(CategoryType.GetValue(RESULTSET.getInt(2))));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfCategories;
    }

    public List<Product> getProducts() {
        connectionToDB();
        List<Product> listOfProducts = new ArrayList<>();
        try (Statement statement = CONNECTION.createStatement()) {
            RESULTSET = statement.executeQuery("SELECT * FROM Products ORDER BY Id");
            while (RESULTSET.next()) {
                Product p = new Product();
                p.setName(RESULTSET.getString(2));
                p.setRate(RESULTSET.getInt(3));
                p.setPrice(RESULTSET.getInt(4));
                listOfProducts.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfProducts;
    }

    //CREATE TABLE USERS
    public void createUsersTable() {
        connectionToDB();
        try (Statement statement = CONNECTION.createStatement()) {
            String sqlCommand2 = "DROP TABLE IF EXISTS Users";
            statement.executeUpdate(sqlCommand2);
            String sqlCommand =
                    "CREATE TABLE Users (" +
                            "Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                            "UserName VARCHAR(20) NOT NULL, " +
                            "Password VARCHAR(20) NOT NULL)";
            statement.execute(sqlCommand);
            System.out.println("Users table has been created");
            statement.executeUpdate("INSERT INTO Users(UserName, Password) VALUES('nastya1', '1234N')");
            statement.executeUpdate("INSERT INTO Users(UserName, Password) VALUES('nastya2', '1235N')");
            statement.executeUpdate("INSERT INTO Users(UserName, Password) VALUES('nastya3', '1236N')");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //CREATE TABLE CART
    public void createCartTable() {
        connectionToDB();
        try (Statement statement = CONNECTION.createStatement()) {
            String sqlCommand2 = "DROP TABLE IF EXISTS Cart";
            statement.executeUpdate(sqlCommand2);
            String sqlCommand =
                    "CREATE TABLE Cart (" +
                            "UserId INT NOT NULL,  " +
                            "ProductId INT NOT NULL," +
                            "TimeStamp DATETIME NOT NULL)";

            statement.execute(sqlCommand);
            System.out.println("Cart table has been created");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Method addToCart
    public void addToCart(Integer userId, Integer productId) {
        connectionToDB();
        try (Statement statement = CONNECTION.createStatement()) {
            String sqlCommand = "INSERT INTO Cart VALUES(" +
                    userId + "," +
                    productId + "," +
                    "curdate()" +
                    ")";
            statement.executeUpdate(sqlCommand);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Method getPurchasedProducts
    public List<Product> getPurchasedProducts(int userId) throws SQLException {
        connectionToDB();
        List<Product> listOfPurchasedProducts = new ArrayList<>();
        try (Statement statement = CONNECTION.createStatement()) {
            String sql = "SELECT Products.Name, Products.Rate, Products.Price" +
                    " FROM Products " +
                    "JOIN Cart ON Products.Id = Cart.ProductId " +
                    "WHERE Cart.UserId = " + userId +
                    " AND TIMEDIFF(Cart.TimeStamp, CURRENT_TIMESTAMP()) <= '00:02:00' ";
            RESULTSET = statement.executeQuery(sql);
            while (RESULTSET.next()) {
                Product p = new Product();
                p.setName(RESULTSET.getString(1));
                p.setRate(RESULTSET.getInt(2));
                p.setPrice(RESULTSET.getInt(3));
                listOfPurchasedProducts.add(p);
            }
        }
        return listOfPurchasedProducts;
    }


}
