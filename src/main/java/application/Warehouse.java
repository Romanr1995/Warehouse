package application;

public class Warehouse {
    private int balanceProducts;

    public Warehouse() {
        this.balanceProducts = 1000;
    }

    public boolean isEmpty() {
        return balanceProducts <= 0;
    }

    public synchronized int getProductsCountWhenBuyiing(int countProducts) throws InterruptedException {
        if (countProducts > balanceProducts) {
            countProducts = balanceProducts;
            balanceProducts = 0;
        } else {
            {
                    balanceProducts -= countProducts;
            }
        }
        return countProducts;
    }
}
