# Sales Analysis (Java)

This project demonstrates data analysis on a CSV sales dataset using **Java Streams and functional programming**.

---

## Description

The application reads sales data from a CSV file and performs the following analyses:

- Total revenue calculation
- Quantity sold by category
- Revenue by product
- Top-selling product
- Average sale value

It demonstrates:

- Functional programming
- Stream operations
- Data aggregation
- Lambda expressions
- Reading CSV files from resources

---

## Project Structure
````
SalesAnalysis/
│── README.md
└── src/
├── main/java/
│ ├── App.java
│ ├── model/SalesRecord.java
│ └── service/SalesAnalyzer.java
├── main/resources/
│ └── sales.csv
└── test/java/
└── SalesAnalyzerTest.java
````
## Setup Instructions

1. Clone the repository:

````
git clone git@github.com:Kunal-ryfl/BuildRound.git
````
2. Open the project in IntelliJ IDEA or another Java IDE.

3. Make sure src/main/resources is marked as Resources Root.

4. Run the main class:
````
App.main()
````
5. Run unit tests from SalesAnalyzerTest.java to validate the analysis methods.

## Sample Output
````
=== Sales Analysis Report ===
Total Revenue: 1250.0

Quantity by Category:
Electronics: 50
Clothing: 30

Revenue by Product:
Laptop: 1000.0
Shirt: 250.0

Top Selling Product: Laptop
Average Sale Value: 62.5
````
