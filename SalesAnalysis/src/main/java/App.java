package SalesAnalysis.src.main.java;

import SalesAnalysis.src.main.java.model.SalesRecord;
import SalesAnalysis.src.main.java.service.SalesAnalyzer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class to run the Sales Analysis application.
 * Reads data from CSV file in resources folder and prints analysis results.
 */
public class App {

    public static void main(String[] args) {

        // Load CSV file from resources folder
        try (InputStream is = App.class.getResourceAsStream("/sales.csv")) {

            if (is == null) {
                System.out.println("ERROR: sales.csv not found in resources!");
                return;
            }

            // Read CSV lines and convert to SalesRecord objects
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                List<SalesRecord> sales = reader.lines()
                        .map(String::trim)
                        .filter(line -> !line.isEmpty())
                        .skip(1) // skip CSV header
                        .map(line -> line.split(","))
                        // protect against malformed rows (should have 5 columns)
                        .filter(arr -> arr.length >= 5)
                        .map(arr -> {
                            String orderId = arr[0].trim();
                            String product = arr[1].trim();
                            String category = arr[2].trim();
                            int quantity = Integer.parseInt(arr[3].trim());
                            double price = Double.parseDouble(arr[4].trim());
                            // SalesRecord constructor: (orderId, product, category, quantity, price)
                            return new SalesRecord(orderId, product, category, quantity, price);
                        })
                        .collect(Collectors.toList());

                // Create analyzer
                SalesAnalyzer analyzer = new SalesAnalyzer(sales);

                // Print analysis results
                System.out.println("=== Sales Analysis Report ===");
                System.out.println("Total Revenue: " + analyzer.getTotalRevenue());

                System.out.println("\nQuantity by Category: ");
                analyzer.getQuantityByCategory().forEach((k, v) -> System.out.println(k + ": " + v));

                System.out.println("\nRevenue by Product: ");
                analyzer.getRevenueByProduct().forEach((k, v) -> System.out.println(k + ": " + v));

                System.out.println("\nTop Selling Product: " + analyzer.getTopSellingProduct());
                System.out.println("Average Sale Value: " + analyzer.getAverageSaleValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
