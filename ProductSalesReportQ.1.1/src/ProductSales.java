/*
 * ProductSales.java
 * This class implements the IProductSales interface and provides
 */

public class ProductSales implements IProductSales {

    // Method to calculate total sales
    @Override
    public int totalSales(int[][] productSales) {
        int total = 0;
        for (int[] year : productSales) {
            for (int quarter : year) {
                total += quarter;
            }
        }
        return total;
    }

    // Method to calculate average sales
    @Override
    public double averageSales(int[][] productSales) {
        int count = 0;
        int total = 0;
        for (int[] year : productSales) {
            for (int quarter : year) {
                total += quarter;
                count++;
            }
        }
        return (double) total / count;
    }

    // Method to find maximum sale
    @Override
    public int maxSale(int[][] productSales) {
        int max = productSales[0][0];
        for (int[] year : productSales) {
            for (int quarter : year) {
                if (quarter > max) {
                    max = quarter;
                }
            }
        }
        return max;
    }

    // Method to find minimum sale
    @Override
    public int minSale(int[][] productSales) {
        int min = productSales[0][0];
        for (int[] year : productSales) {
            for (int quarter : year) {
                if (quarter < min) {
                    min = quarter;
                }
            }
        }
        return min;
    }
}


