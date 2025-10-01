# Assignment 2: Insertion Sort Implementation

## 📋 Project Information
- **Course**: Design and Analysis of Algorithms
- **Algorithm**: Insertion Sort with optimizations for nearly-sorted data
- **Student**: [Dana Bekbosyn]
- **Partner**: [Birkhanym Kazhymukhamet] (Selection Sort)
- **Build System**: Maven

## 🏗️ Project Structure
assignment2-insertion-sort/
├── src/
│ ├── main/java/
│ │ ├── algorithms/InsertionSort.java
│ │ ├── metrics/PerformanceTracker.java
│ │ └── cli/BenchmarkRunner.java
│ └── test/java/
│ └── algorithms/InsertionSortTest.java
├── docs/
│ ├── analysis-report.pdf
│ └── performance-plots/
├── pom.xml
└── README.md

text

## 🚀 Build & Test
```bash
# Compile project
mvn compile

# Run tests
mvn test

# Package project
mvn package
📊 Features Implemented

✅ Insertion Sort with binary search optimization
✅ Performance metrics tracking (comparisons, assignments, swaps)
✅ Optimizations for nearly-sorted data
✅ Comprehensive unit tests
✅ CLI benchmark runner
✅ Memory-efficient implementation
🎯 Algorithm Specifications

Time Complexity:

Best Case: O(n) - nearly sorted data
Average Case: O(n²)
Worst Case: O(n²)
Space Complexity: O(1) - in-place sorting
Optimizations: Binary search insertion, early termination for sorted segments
👥 Peer Review

My Algorithm: Insertion Sort
Partner's Algorithm: Selection Sort
Cross-review: Complexity analysis and code optimization suggestions
📈 Performance Metrics Tracked

Number of comparisons
Number of assignments/swaps
Execution time
Memory usage patterns
🔧 Dependencies

JUnit 5 - Testing framework
Maven - Build automation
Created for Algorithmic Analysis Assignment 2

