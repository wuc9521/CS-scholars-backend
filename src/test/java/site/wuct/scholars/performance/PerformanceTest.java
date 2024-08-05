package site.wuct.scholars.performance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PerformanceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Test the performance of a simple query
     */
    @Test
    public void testLargeDatasetQuery() {
        System.out.println("Running testLargeDatasetQuery");
        long startTime = System.currentTimeMillis();
        jdbcTemplate.query(
                "SELECT p.name, p.major, l.loc_name, l.country FROM people p JOIN \"in\" i ON p.pid = i.pid JOIN locations l ON i.locid = l.locid",
                (rs, rowNum) -> null);
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("Large dataset query time: " + duration + " ms");
        assertTrue(duration < 5000, "Query took too long");
    }

    /**
     * Test the performance of concurrent connections
     * 
     * @throws InterruptedException if the thread is interrupted
     */
    @Test
    public void testConcurrentConnections() throws InterruptedException {
        System.out.println("Running testConcurrentConnections");
        int numThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> jdbcTemplate.query(
                    "SELECT p.name, l.loc_name, l.country FROM people p " +
                            "JOIN \"in\" i ON p.pid = i.pid " +
                            "JOIN locations l ON i.locid = l.locid " +
                            "ORDER BY p.name LIMIT 100",
                    (rs, rowNum) -> null));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("Concurrent connections query time: " + duration + " ms");
        assertTrue(duration < 10000, "Concurrent queries took too long");
    }

    /**
     * Test the performance of a complex join operation
     */
    @Test
    public void testComplexJoinOperation() {
        System.out.println("Running testComplexJoinOperation");
        long startTime = System.currentTimeMillis();
        jdbcTemplate.query(
                "SELECT p.name, p.major, l.loc_name, l.country, g.budget_start, " +
                        "COUNT(DISTINCT pu.pubid) as publication_count " +
                        "FROM people p " +
                        "JOIN \"in\" i ON p.pid = i.pid " +
                        "JOIN locations l ON i.locid = l.locid " +
                        "LEFT JOIN has h ON p.pid = h.pid " +
                        "LEFT JOIN grants g ON h.grantid = g.grantid " +
                        "LEFT JOIN publish pu ON p.pid = pu.pid " +
                        "GROUP BY p.pid, l.locid, g.grantid " +
                        "ORDER BY publication_count DESC " +
                        "LIMIT 1000",
                (rs, rowNum) -> null);
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("Complex join operation time: " + duration + " ms");
        assertTrue(duration < 35000, "Complex join took too long");
    }

    /**
     * Test the impact of indexing on query performance
     */
    @Test
    public void testIndexImpact() throws SQLException {
        jdbcTemplate.execute("DROP INDEX IF EXISTS idx_people_name"); // Ensure the index doesn't exist
        BiFunction<String, Integer, Long> runQueries = (indexStatus, iterations) -> { // Function to run test queries
            long totalTime = 0;
            for (int i = 0; i < iterations; i++) { // Generate a random string to search for
                String randomString = generateRandomString(3);
                String query = "SELECT * FROM people WHERE name LIKE '" + randomString + "%' LIMIT 100";
                // Flush cache by querying large amount of unrelated data
                jdbcTemplate.query("SELECT * FROM people ORDER BY RANDOM() LIMIT 10000", (rs, rowNum) -> null);
                long startTime = System.nanoTime();
                List<Map<String, Object>> results = jdbcTemplate.queryForList(query);
                totalTime += System.nanoTime() - startTime;
                System.out.println("Query for '" + randomString + "' returned " + results.size() + " results");
            }
            double avgTime = totalTime / iterations / 1_000_000.0;
            System.out.println("Average time " + indexStatus + " index: " + avgTime + " ms");
            return totalTime;
        };
        long timeWithoutIndex = runQueries.apply("without", 20); // Test without index
        jdbcTemplate.execute("CREATE INDEX IF NOT EXISTS idx_people_name ON people(name)"); // Create the index
        long timeWithIndex = runQueries.apply("with", 20); // Test with index
        jdbcTemplate.execute("DROP INDEX IF EXISTS idx_people_name"); // Clean up
        System.out.println("Time without index: " + (timeWithoutIndex / 20 / 1_000_000.0) + " ms");
        System.out.println("Time with index: " + (timeWithIndex / 20 / 1_000_000.0) + " ms");
        assertTrue(timeWithIndex < timeWithoutIndex,
                "Index did not improve performance. Without index: " + (timeWithoutIndex / 20 / 1_000_000.0) +
                        " ms, With index: " + (timeWithIndex / 20 / 1_000_000.0) + " ms");
    }

    /**
     * Generate a random string of a given length
     * 
     * @param length the length of the string
     * @return the random string
     */
    private String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (chars.length() * Math.random());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Test the performance of a sustained load
     * 
     * @throws InterruptedException if the thread is interrupted
     */
    @Test
    public void testSustainedLoad() throws InterruptedException {
        System.out.println("Running testSustainedLoad");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            System.out.println("Request " + i);
            executorService.submit(() -> jdbcTemplate.query(
                    "SELECT p.name, p.major, l.loc_name, l.country, COUNT(DISTINCT g.grantid) as grant_count " +
                            "FROM people p " +
                            "JOIN \"in\" i ON p.pid = i.pid " +
                            "JOIN locations l ON i.locid = l.locid " +
                            "LEFT JOIN has h ON p.pid = h.pid " +
                            "LEFT JOIN grants g ON h.grantid = g.grantid " +
                            "GROUP BY p.pid, l.locid " +
                            "ORDER BY grant_count DESC " +
                            "LIMIT 10",
                    (rs, rowNum) -> null));
            Thread.sleep(50); // Reduce delay between requests
        }

        executorService.shutdown();
        executorService.awaitTermination(3, TimeUnit.MINUTES);
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("Sustained load test time: " + duration + " ms");
        assertTrue(duration < 200000, "Sustained load test took too long"); // Adjusted time expectation
    }
}
