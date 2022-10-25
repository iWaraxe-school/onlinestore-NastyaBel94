package by.issoft.store;

import java.util.concurrent.ThreadLocalRandom;

public class CreateOrder extends Thread {
    int numberOfProduct;

    public CreateOrder(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
   }

    public void run() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long countOfSeconds = random.nextLong(1, 30);
        System.out.println(this);
        try {
            Thread.sleep(countOfSeconds * 1000);
            Store.getInstance().addToCart(numberOfProduct);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

