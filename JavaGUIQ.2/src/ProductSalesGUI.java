/*
 * ProductSalesGUI.java
 * Swing GUI application for Question 2 (2.1 - 2.4).
 *
 * - Two Buttons: Load Product Data, Save Product Data
 * - TextArea to display product sales data (editable by user but Save writes the current area)
 * - Read-only label to show number of years processed
 * - Menu: File -> Exit ; Tools -> Load Product Data, Save Product Data, Clear
 * - Save writes a file named "data.txt" in the program working directory
 *
 * Keep this simple and commented for exam marking.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProductSalesGUI extends JFrame {

    // GUI components
    private JButton btnLoad;
    private JButton btnSave;
    private JTextArea txtArea;
    private JLabel lblYearsProcessed;

    // Sales limit (given by the retailer)
    private final int SALES_LIMIT = 500;

    // Sales data stored in memory (hard-coded as requested)
    // Rows = years, Columns = products: [microphone, speakers, mixing desk]
    private final int[][] salesDataInMemory = {
        {300, 150, 700},  // Year 1: microphone, speakers, mixing desk
        {250, 200, 600}   // Year 2: microphone, speakers, mixing desk
    };

    // Object that will perform calculations (from 2.4)
    private ProductSales productSalesCalculator;

    // Constructor: build GUI
    public ProductSalesGUI() {
        setTitle("Product Sales - Sound Equipment Retailer");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen

        // Create components
        btnLoad = new JButton("Load Product Data");
        btnSave = new JButton("Save Product Data");
        txtArea = new JTextArea(12, 40);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtArea);

        // Read-only label for years processed
        lblYearsProcessed = new JLabel("Years processed: 0");
        lblYearsProcessed.setEnabled(false); // visually hint read-only

        // Panel for top buttons and label
        JPanel topPanel = new JPanel();
        topPanel.add(btnLoad);
        topPanel.add(btnSave);
        topPanel.add(new JLabel("   ")); // spacer
        topPanel.add(lblYearsProcessed);

        // Add menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem menuExit = new JMenuItem("Exit");
        fileMenu.add(menuExit);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem menuLoad = new JMenuItem("Load Product Data");
        JMenuItem menuSave = new JMenuItem("Save Product Data");
        JMenuItem menuClear = new JMenuItem("Clear");
        toolsMenu.add(menuLoad);
        toolsMenu.add(menuSave);
        toolsMenu.add(menuClear);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // Layout
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(topPanel, BorderLayout.NORTH);
        c.add(scroll, BorderLayout.CENTER);

        // Initialize ProductSales with data and limit
        productSalesCalculator = new ProductSales(salesDataInMemory, SALES_LIMIT);

        // Wire button actions
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadProductData();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        // Menu items call same logic (as requested)
        menuLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadProductData();
            }
        });

        menuSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        menuClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });
    }

    // Action: Load product data from memory and calculate stats
    private void loadProductData() {
        // Use the ProductSales object to get calculations
        int total = productSalesCalculator.GetTotalSales();
        double avgExact = productSalesCalculator.GetAverageSales();
        int avgRounded = (int) Math.round(avgExact);
        int over = productSalesCalculator.GetSalesOverLimit();
        int under = productSalesCalculator.GetSalesUnderLimit();
        int years = productSalesCalculator.GetProductsProcessed();

        // Build a friendly text report for the text area
        StringBuilder sb = new StringBuilder();
        sb.append("Product Sales Data (loaded from memory):\n");
        sb.append("----------------------------------------\n");

        // List each year's product sales
        for (int y = 0; y < salesDataInMemory.length; y++) {
            sb.append("Year ").append(y + 1).append(": ");
            sb.append("Microphone ").append(salesDataInMemory[y][0]).append(", ");
            sb.append("Speakers ").append(salesDataInMemory[y][1]).append(", ");
            sb.append("Mixing Desk ").append(salesDataInMemory[y][2]).append("\n");
        }

        sb.append("\n");
        sb.append("Calculated Results:\n");
        sb.append("TOTAL SALES: ").append(total).append("\n");
        sb.append("AVERAGE SALES: ").append(avgRounded).append("\n");
        sb.append("SALES OVER LIMIT (" + SALES_LIMIT + "): ").append(over).append("\n");
        sb.append("SALES UNDER/OR EQUAL TO LIMIT (" + SALES_LIMIT + "): ").append(under).append("\n");

        // Display in text area
        txtArea.setText(sb.toString());

        // Update the years processed label (read-only)
        lblYearsProcessed.setText("Years processed: " + years);
    }

    // Action: Save current text area content to data.txt in required format
    private void saveToFile() {
        // We will write the specific format requested in the task regardless of what is in the text area:
        // Data log
        // ***** (a line of stars)
        // total sales: 2200
        // average sales 367
        // sales over limit 2
        // sales under limit 4

        // To ensure accuracy use the calculator again (in case text area was edited)
        int total = productSalesCalculator.GetTotalSales();
        double avgExact = productSalesCalculator.GetAverageSales();
        int avgRounded = (int) Math.round(avgExact);
        int over = productSalesCalculator.GetSalesOverLimit();
        int under = productSalesCalculator.GetSalesUnderLimit();

        StringBuilder sb = new StringBuilder();
        sb.append("Data log\n");
        // Create a line of stars (choose 30 stars for visibility)
        sb.append("******************************\n");
        sb.append("total sales: ").append(total).append("\n");
        sb.append("average sales ").append(avgRounded).append("\n");
        sb.append("sales over limit ").append(over).append("\n");
        sb.append("sales under limit ").append(under).append("\n");

        // Write file (data.txt) in current working directory
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
            writer.write(sb.toString());
            // Inform the user
            JOptionPane.showMessageDialog(this, "Data saved to data.txt", "Save Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Action: clear text area and reset years processed label
    private void clearFields() {
        txtArea.setText("");
        lblYearsProcessed.setText("Years processed: 0");
    }

    // Action: exit application
    private void exitApp() {
        // Confirm before exit (good exam practice)
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Main entry point
    public static void main(String[] args) {
        // Swing: create GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProductSalesGUI gui = new ProductSalesGUI();
                gui.setVisible(true);
            }
        });
    }
}

