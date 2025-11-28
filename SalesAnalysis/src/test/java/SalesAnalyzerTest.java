package SalesAnalysis.src.test.java;

import SalesAnalysis.src.main.java.model.SalesRecord;
import SalesAnalysis.src.main.java.service.SalesAnalyzer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SalesAnalyzerTest {

    private final List<SalesRecord> sample = List.of(
            new SalesRecord("1", "Laptop", "Electronics", 2, 50000),
            new SalesRecord("2", "Mouse", "Electronics", 3, 1000),
            new SalesRecord("3", "Shirt", "Clothing", 2, 1200)
    );

    private final SalesAnalyzer analyzer = new SalesAnalyzer(sample);

    @Test
    void testTotalRevenue() {
        assertEquals(2*50000 + 3*1000 + 2*1200, analyzer.getTotalRevenue());
    }

    @Test
    void testQuantityByCategory() {
        Map<String, Integer> result = analyzer.getQuantityByCategory();
        assertEquals(5, result.get("Electronics"));
        assertEquals(2, result.get("Clothing"));
    }

    @Test
    void testRevenueByProduct() {
        Map<String, Double> result = analyzer.getRevenueByProduct();
        assertEquals(100000, result.get("Laptop"));
        assertEquals(3000, result.get("Mouse"));
    }

    @Test
    void testTopSellingProduct() {
        assertEquals("Mouse", analyzer.getTopSellingProduct());
    }

    @Test
    void testAverageSaleValue() {
        double expected = (100000 + 3000 + 2400) / 3.0;
        assertEquals(expected, analyzer.getAverageSaleValue());
    }
}
