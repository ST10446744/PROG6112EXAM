/*
 * IProductSales.java
 * Interface defining methods for product sales calculations.
 */

public interface IProductSales {
    int totalSales(int[][] productSales);
    double averageSales(int[][] productSales);
    int maxSale(int[][] productSales);
    int minSale(int[][] productSales);
}
