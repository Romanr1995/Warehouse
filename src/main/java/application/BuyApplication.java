package application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class BuyApplication {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 1) {
            int countBuyers = Integer.parseInt(args[0].trim());
            Warehouse warehouse = new Warehouse();
            CyclicBarrier cyclicBarrier = new CyclicBarrier(countBuyers);
            List<Buyer> buyers = new ArrayList<>();
            for (int i = 0; i < countBuyers; i++) {
                Buyer buyer = new Buyer(warehouse, cyclicBarrier);
                buyers.add(buyer);
                buyer.start();
            }

            for (Buyer buyer : buyers) {
                buyer.join();
            }

            int products = 0;
            for (Buyer buyer : buyers) {
                System.out.println(buyer);
                products+=buyer.getCountProducts();
            }
            System.out.println("Количество товара,купленное всеми покупателями = " + products);
        } else {
            System.out.println("Ошибка!Должно быть задано 1 аргумент.А задано: " + args.length);
        }
    }
}
