package application;

public class Warehouse {
    private volatile int balanceProducts;

    public Warehouse() {
        this.balanceProducts = 1000;
    }

    public boolean isEmpty() {
        return balanceProducts <= 0;
    }

    public int getProductsCountWhenBuyiing(int countProducts) {
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
