
/*
 * ProductSalesReport.java
 */

public class ProductSalesReport {

    public static void main(String[] args) {
        // 2D array: rows = years, columns = quarters
        int[][] sales2D = {
            {300, 150, 700}, // Year 1: Q1, Q2, Q3
            {250, 200, 600}  // Year 2: Q1, Q2, Q3
        };

        // Print the header 
        System.out.println("PRODUCT SALES REPORT - 2025");
        System.out.println("---------------------------");

       
        int totalEntries = sales2D.length * sales2D[0].length; // 2 * 3 = 6
        int[] sales1D = new int[totalEntries];

        int idx = 0;
        for (int year = 0; year < sales2D.length; year++) {
            for (int q = 0; q < sales2D[year].length; q++) {
                sales1D[idx] = sales2D[year][q];
                idx++;
            }
        }

        // Calculate total, max, min using the 1D array 
        int total = 0;
        int max = sales1D[0];
        int min = sales1D[0];

        for (int i = 0; i < sales1D.length; i++) {
            int value = sales1D[i];
            total += value;

            if (value > max) {
                max = value;
            }

            if (value < min) {
                min = value;
            }
        }

        
        double averageExact = (double) total / sales1D.length;
        int averageRounded = (int) Math.round(averageExact);

        // Output the results
        System.out.println("TOTAL SALES: " + total);
        System.out.println("AVAERAGE SALES: " + averageRounded);
        System.out.println("MAXIUM SALE: " + max);
        System.out.println("MINIUM SALE: " + min);

        
    }
}
