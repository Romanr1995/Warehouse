package application;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Buyer extends Thread {

    private int countProducts;
    private int countBuys;
    private static int numberBuyer = 0;
    private String name = "Buyer";
    private Warehouse warehouse;
    private CyclicBarrier cyclicBarrier;

    public Buyer(Warehouse warehouse, CyclicBarrier cyclicBarrier) {
        this.name += ++numberBuyer;
        this.warehouse = warehouse;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public synchronized void run() {
        while (!warehouse.isEmpty()) {
            try {
                cyclicBarrier.await();
                int productsCountWhenBuyiing = Warehouse.getProductsCountWhenBuyiing(1 +
                        (int) (Math.random() * 10));
                if (productsCountWhenBuyiing > 0) {
                    countBuys++;
                    countProducts += productsCountWhenBuyiing;
                }
                cyclicBarrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                System.out.println("Поток " + name + "остановлен.");
            }
        }
    }

    @Override
    public String toString() {
        return name + ",количество покупок = " + countBuys + ",количество купленного товара = " + countProducts;
    }
}
