package SalesAnalysis.src.test.java;

import SalesAnalysis.src.main.java.model.SalesRecord;
import SalesAnalysis.src.main.java.service.SalesAnalyzer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SalesAnalyzerTest {
    // Sample test data (independent from CSV)
    private final List<SalesRecord> sample = List.of(
            new SalesRecord("1", "Laptop", "Electronics", 2, 50000),
            new SalesRecord("2", "Mouse", "Electronics", 3, 1000),
            new SalesRecord("3", "Shirt", "Clothing", 2, 1200)
    );

    private final SalesAnalyzer analyzer = new SalesAnalyzer(sample);

    @Test
    void testTotalRevenue() {
        double expected =
                        (2 * 50000) +     // Laptop
                        (3 * 1000) +      // Mouse
                        (2 * 1200);       // Shirt

        assertEquals(expected, analyzer.getTotalRevenue());
    }

    @Test
    void testQuantityByCategory() {
        Map<String, Integer> result = analyzer.getQuantityByCategory();

        assertEquals(5, result.get("Electronics")); // 2 + 3
        assertEquals(2, result.get("Clothing"));    // 2
    }

    @Test
    void testRevenueByProduct() {
        Map<String, Double> result = analyzer.getRevenueByProduct();

        assertEquals(100000.0, result.get("Laptop")); // 2 * 50000
        assertEquals(3000.0, result.get("Mouse"));    // 3 * 1000
        assertEquals(2400.0, result.get("Shirt"));    // 2 * 1200
    }

    @Test
    void testTopSellingProduct() {
        assertEquals("Mouse", analyzer.getTopSellingProduct()); // Highest quantity = 3
    }

    @Test
    void testAverageSaleValue() {
        double expected =
                (100000.0 + 3000.0 + 2400.0) / 3;

        assertEquals(expected, analyzer.getAverageSaleValue());
    }
}
