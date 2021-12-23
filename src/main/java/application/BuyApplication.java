package application;

import java.util.concurrent.CyclicBarrier;

public class BuyApplication {
    public static void main(String[] args) {
        if (args.length == 1) {
            int countBuyers = Integer.parseInt(args[0].trim());
            Warehouse warehouse = new Warehouse();
            CyclicBarrier cyclicBarrier = new CyclicBarrier(countBuyers);
            for (int i = 0; i < countBuyers; i++) {
                Buyer buyer = new Buyer(warehouse, cyclicBarrier);
                buyer.start();
            }

        } else {
            System.out.println("Ошибка!Должно быть задано 1 аргумент.А задано: " + args.length);
        }
    }
}
