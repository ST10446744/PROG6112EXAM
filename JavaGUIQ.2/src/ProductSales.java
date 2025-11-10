/*
 * ProductSales.java
 * Implements IProductSales interface and provides calculation logic.
 *
 * Uses simple loops only (suitable for exam). Sales limit is provided to constructor.
 */

public class ProductSales implements IProductSales {

    // Member variables
    private int[][] productSales;   // sales data: rows = years, columns = products
    private int salesLimit;         // threshold to decide over/under success

    // Cached calculations (computed when requested)
    private boolean computed = false;
    private int total = 0;
    private int overCount = 0;
    private int underCount = 0;
    private double average = 0.0;

    // Constructor: receives sales data and the sales limit
    public ProductSales(int[][] productSales, int salesLimit) {
        // Defensive copy (simple): just assign reference (ok for exam / small app)
        this.productSales = productSales;
        this.salesLimit = salesLimit;
    }

    // Returns the raw sales array
    @Override
    public int[][] GetProductSales() {
        return productSales;
    }

    // Returns the number of years processed (rows in array)
    @Override
    public int GetProductsProcessed() {
        if (productSales == null) {
            return 0;
        }
        return productSales.length;
    }

    // Compute totals, average, over/under counts (simple single pass)
    private void computeIfNeeded() {
        if (computed || productSales == null) {
            return;
        }

        int count = 0;
        total = 0;
        overCount = 0;
        underCount = 0;

        for (int i = 0; i < productSales.length; i++) {
            for (int j = 0; j < productSales[i].length; j++) {
                int value = productSales[i][j];
                total += value;
                count++;

                // Over limit > salesLimit counts as success over limit
                if (value > salesLimit) {
                    overCount++;
                } else {
                    // under or equal limit counted as under
                    underCount++;
                }
            }
        }

        if (count > 0) {
            average = (double) total / count;
        } else {
            average = 0.0;
        }

        computed = true;
    }

    @Override
    public int GetTotalSales() {
        computeIfNeeded();
        return total;
    }

    @Override
    public int GetSalesOverLimit() {
        computeIfNeeded();
        return overCount;
    }

    @Override
    public int GetSalesUnderLimit() {
        computeIfNeeded();
        return underCount;
    }

    @Override
    public double GetAverageSales() {
        computeIfNeeded();
        return average;
    }
}


