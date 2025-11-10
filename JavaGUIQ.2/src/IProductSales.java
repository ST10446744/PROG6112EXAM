/*
 * IProductSales.java
 * Interface that defines the required product sales methods (Question 2.4)
 *
 * Student-level, simple method signatures as requested.
 */

public interface IProductSales {
    int[][] GetProductSales();          // returns the raw product sales (years x products)
    int GetTotalSales();                // returns total sales across all years/products
    int GetSalesOverLimit();            // returns count of sales entries > sales limit
    int GetSalesUnderLimit();           // returns count of sales entries <= sales limit
    int GetProductsProcessed();         // returns number of years processed (rows in array)
    double GetAverageSales();           // returns average (exact double)
}


