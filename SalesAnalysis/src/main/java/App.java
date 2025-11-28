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
                        .skip(1) // skip CSV header
                        .map(line -> line.split(","))
                        .map(arr -> new SalesRecord(
                                arr[0],        // productName
                                arr[1],        // category
                                arr[2],        // date
                                Integer.parseInt(arr[3]),  // quantity
                                Double.parseDouble(arr[4]) // price
                        ))
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