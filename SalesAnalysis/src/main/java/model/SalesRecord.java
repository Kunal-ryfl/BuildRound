package SalesAnalysis.src.main.java.model;

/**
 * Represents a single sales record from the CSV file.
 * Contains product details, category, date, quantity sold, and price.
 */
public class SalesRecord {

    private String productName;
    private String category;
    private String date;
    private int quantity;
    private double price;

    // Constructor
    public SalesRecord(String productName, String category, String date, int quantity, double price) {
        this.productName = productName;
        this.category = category;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public String getDate() { return date; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    // Helper method to calculate total sale value for this record
    public double getTotal() {
        return quantity * price;
    }
}
