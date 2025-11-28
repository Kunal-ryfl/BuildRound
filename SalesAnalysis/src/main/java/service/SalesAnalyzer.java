package SalesAnalysis.src.main.java.service;

import SalesAnalysis.src.main.java.model.SalesRecord;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Performs various analysis operations on sales data.
 * Uses Java Streams and functional programming to aggregate and group data.
 */
public class SalesAnalyzer {

    private final List<SalesRecord> sales;

    // Constructor
    public SalesAnalyzer(List<SalesRecord> sales) {
        this.sales = sales;
    }

    /**
     * Calculates total revenue from all sales records.
     * Revenue = quantity * price for each sale.
     */
    public double getTotalRevenue() {
        return sales.stream()
                .mapToDouble(SalesRecord::getTotal)
                .sum();
    }

    /**
     * Groups total quantity sold by product category.
     */
    public Map<String, Integer> getQuantityByCategory() {
        return sales.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getCategory,
                        Collectors.summingInt(SalesRecord::getQuantity)
                ));
    }

    /**
     * Calculates total revenue for each product.
     */
    public Map<String, Double> getRevenueByProduct() {
        return sales.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getProductName,
                        Collectors.summingDouble(SalesRecord::getTotal)
                ));
    }

    /**
     * Finds the top-selling product based on quantity sold.
     */
    public String getTopSellingProduct() {
        return sales.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getProductName,
                        Collectors.summingInt(SalesRecord::getQuantity)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No sales");
    }

    /**
     * Calculates the average sale value across all sales records.
     * Average = (sum of total revenue of each sale) / number of sales entries.
     */
    public double getAverageSaleValue() {
        return sales.stream()
                .mapToDouble(SalesRecord::getTotal)
                .average()
                .orElse(0.0);
    }
}
