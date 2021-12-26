package application;

import java.util.concurrent.Phaser;

public class Buyer extends Thread {

    private int countProducts;
    private int countBuys;
    private static int numberBuyer;
    private String name = "Buyer";
    private Warehouse warehouse;
    private Phaser phaser;

    public Buyer(Warehouse warehouse, Phaser phaser) {
        this.name += ++numberBuyer;
        this.warehouse = warehouse;
        this.phaser = phaser;
        this.phaser.register();
    }

    @Override
    public void run() {

        while (!warehouse.isEmpty()) {
            try {
                phaser.arriveAndAwaitAdvance();
                int productsCountWhenBuying = warehouse.getProductsCountWhenBuyiing(1 +
                        (int) (Math.random() * 10));
                if (productsCountWhenBuying > 0) {
                    countBuys++;
                    countProducts += productsCountWhenBuying;
                }

            } catch (InterruptedException e) {
                System.out.println("Поток " + name + "остановлен.");
            }
        }
        System.out.println(this);
        phaser.arriveAndDeregister();
    }

    @Override
    public String toString() {
        return name + ",количество покупок = " + countBuys + ",количество купленного товара = " + countProducts;
    }
}
