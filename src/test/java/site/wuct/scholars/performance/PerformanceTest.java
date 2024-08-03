package site.wuct.scholars.performance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PerformanceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testLargeDatasetQuery() {
        long startTime = System.currentTimeMillis();
        jdbcTemplate.query("SELECT p.name, p.major, l.loc_name, l.country FROM people p JOIN \"in\" i ON p.pid = i.pid JOIN locations l ON i.locid = l.locid", (rs, rowNum) -> null);
        long endTime = System.currentTimeMillis();
        
        long duration = endTime - startTime;
        System.out.println("Large dataset query time: " + duration + " ms");
        assertTrue(duration < 5000, "Query took too long");
    }

    @Test
    public void testConcurrentConnections() throws InterruptedException {
        int numThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> jdbcTemplate.query("SELECT p.name, pub.doi FROM people p JOIN publish pu ON p.pid = pu.pid JOIN publications pub ON pu.pubid = pub.pubid LIMIT 100", (rs, rowNum) -> null));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        long endTime = System.currentTimeMillis();
        
        long duration = endTime - startTime;
        System.out.println("Concurrent connections query time: " + duration + " ms");
        assertTrue(duration < 10000, "Concurrent queries took too long");
    }

    @Test
    public void testComplexJoinOperation() {
        long startTime = System.currentTimeMillis();
        jdbcTemplate.query(
            "SELECT p.name, p.major, l.loc_name, pub.doi, g.budget_start " +
            "FROM people p " +
            "JOIN \"in\" i ON p.pid = i.pid " +
            "JOIN locations l ON i.locid = l.locid " +
            "JOIN publish pu ON p.pid = pu.pid " +
            "JOIN publications pub ON pu.pubid = pub.pubid " +
            "LEFT JOIN has h ON p.pid = h.pid " +
            "LEFT JOIN grants g ON h.grantid = g.grantid",
            (rs, rowNum) -> null
        );
        long endTime = System.currentTimeMillis();
        
        long duration = endTime - startTime;
        System.out.println("Complex join operation time: " + duration + " ms");
        assertTrue(duration < 7000, "Complex join took too long");
    }

    @Test
    public void testIndexImpact() {
        // Assuming 'name' column is not indexed
        long startTimeWithoutIndex = System.currentTimeMillis();
        jdbcTemplate.query("SELECT * FROM people WHERE name = 'John Doe'", (rs, rowNum) -> null);
        long endTimeWithoutIndex = System.currentTimeMillis();
        
        // Now add an index (this should be done in a separate migration script in practice)
        jdbcTemplate.execute("CREATE INDEX IF NOT EXISTS idx_people_name ON people(name)");
        
        long startTimeWithIndex = System.currentTimeMillis();
        jdbcTemplate.query("SELECT * FROM people WHERE name = 'John Doe'", (rs, rowNum) -> null);
        long endTimeWithIndex = System.currentTimeMillis();
        
        System.out.println("Query time without index: " + (endTimeWithoutIndex - startTimeWithoutIndex) + " ms");
        System.out.println("Query time with index: " + (endTimeWithIndex - startTimeWithIndex) + " ms");
        assertTrue((endTimeWithIndex - startTimeWithIndex) < (endTimeWithoutIndex - startTimeWithoutIndex), "Index did not improve performance");
        
        // Clean up: remove the index
        jdbcTemplate.execute("DROP INDEX IF EXISTS idx_people_name");
    }

    @Test
    public void testSustainedLoad() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> jdbcTemplate.query(
                "SELECT p.name, p.major, l.loc_name, COUNT(pub.pubid) as publication_count " +
                "FROM people p " +
                "JOIN \"in\" i ON p.pid = i.pid " +
                "JOIN locations l ON i.locid = l.locid " +
                "LEFT JOIN publish pu ON p.pid = pu.pid " +
                "LEFT JOIN publications pub ON pu.pubid = pub.pubid " +
                "GROUP BY p.pid, l.locid " +
                "ORDER BY publication_count DESC " +
                "LIMIT 10", 
                (rs, rowNum) -> null
            ));
            Thread.sleep(100); // Simulate delay between requests
        }
        
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.MINUTES);
        long endTime = System.currentTimeMillis();
        
        long duration = endTime - startTime;
        System.out.println("Sustained load test time: " + duration + " ms");
        assertTrue(duration < 120000, "Sustained load test took too long");
    }
}