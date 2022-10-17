package by.issoft.store;

import by.issoft.domain.*;
import com.github.javafaker.Faker;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;

public class RandomStorePopulator {

    private static Faker faker = new Faker();

    public static void fillStoreRandomly() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("by.issoft.domain");
        Set<Class<?>> subTypes = reflections.get(SubTypes.of(Category.class).asClass());

        CategoryType[] categoryType = CategoryType.values();
        CategoryFactory factory = new CategoryFactory();
        for (CategoryType type : categoryType) {

            Categories category = factory.getCategory(type);

            for (int i = faker.number().numberBetween(1, 10); i > 0; --i) {
                Product product = new Product();

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
                product.setName(productName);
                product.setPrice(faker.number().numberBetween(1, 1000));
                product.setRate(faker.number().numberBetween(0, 10));
                category.addProduct(product);

            }
            Store.getInstance().addCategory(category);
        }
    }
}