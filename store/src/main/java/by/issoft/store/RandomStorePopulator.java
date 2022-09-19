package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import com.github.javafaker.Faker;
import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import static org.reflections.scanners.Scanners.SubTypes;

public class RandomStorePopulator {

    private static Faker faker = new Faker();

    public static void FillStoreRandomly(Store store) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("by.issoft.domain");
        Set<Class<?>> subTypes = reflections.get(SubTypes.of(Category.class).asClass());

        for (Class type : subTypes) {

            Category category = (Category) type.getConstructor().newInstance();

            for (int i = faker.number().numberBetween(1, 10); i > 0; --i) {
                Product product = new Product();
                product.setName(faker.name().name());
                product.setPrice(faker.number().numberBetween(1, 1000));
                product.setRate(faker.number().numberBetween(0, 10));
                category.getProductList().add(product);
            }
            store.addCategory(category);
        }
    }
}
