# Cultivation - LeetCode & HackerRank Practice

A Maven project for practicing coding problems from LeetCode and HackerRank using Java 15.

## Project Structure

```
cultivation/
├── pom.xml                          # Maven configuration
├── README.md                        # This file
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── practice/
    │               ├── Main.java     # Main entry point
    │               ├── leetcode/     # LeetCode problems
    │               ├── hackerrank/   # HackerRank problems
    └── test/
        └── java/
            └── com/
                └── practice/        # Unit tests
```

## Setup Requirements

- Java 15 or higher
- Maven 3.6 or higher

## Maven Commands

### Build the project
```bash
mvn clean compile
```

### Run tests
```bash
mvn test
```

### Run the main class
```bash
mvn exec:java
```

### Package as JAR
```bash
mvn clean package
```

## Package Organization

### LeetCode Problems (`com.practice.leetcode`)
- Each LeetCode problem has its own class
- Follows LeetCode naming convention (e.g., `TwoSum.java`)
- Includes time and space complexity analysis
- Contains test methods for verification

### HackerRank Problems (`com.practice.hackerrank`)
- HackerRank specific problems
- Includes multiple solution approaches when applicable
- Contains test methods for verification

## Example Usage

### Running a specific problem
```bash
# Compile and run TwoSum problem
mvn compile exec:java -Dexec.mainClass="com.practice.leetcode.TwoSum"
```

### Running tests for a specific problem
```bash
mvn test -Dtest=TwoSumTest
```

## Adding New Problems

1. Create a new Java class in the appropriate package (`leetcode` or `hackerrank`)
2. Implement the solution with proper documentation
3. Add unit tests in the `src/test/java/com/practice/` directory
4. Update this README with the new problem details

## Dependencies

- JUnit 5: Testing framework
- Apache Commons Lang: Additional utilities
- Lombok: Reduces boilerplate code

## IDE Configuration

### IntelliJ IDEA
1. Import as Maven project
2. Set Project SDK to Java 15
3. Enable annotation processing for Lombok

### Eclipse
1. Import as Maven project
2. Configure Java build path to Java 15
3. Install Lombok plugin

## Contributing

- Follow Java naming conventions
- Add proper documentation (JavaDoc)
- Include time and space complexity analysis
- Write comprehensive unit tests
- Update README for new problems

## Happy Coding! 🚀
