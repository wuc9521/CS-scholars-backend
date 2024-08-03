package site.wuct.scholars.repository;

import site.wuct.scholars.model.Person;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PeopleRepository extends JpaRepository<Person, Integer> {

        @Query(nativeQuery = true, value = "SELECT p.pid, p.name, p.major, p.hindex, " +
                        "l.loc_name AS location, l.locid as location_id, " +
                        "(SELECT COUNT(*) FROM Publish pub WHERE pub.pid = p.pid) AS publication_count, " +
                        "(SELECT COUNT(*) FROM Has o WHERE o.pid = p.pid) AS grant_count " +
                        "FROM People p " +
                        "JOIN \"in\" i ON p.pid = i.pid " +
                        "JOIN Locations l ON i.locid = l.locid " +
                        "WHERE p.pid = :personId")
        Map<String, Object> getPersonProfile(@Param("personId") Long personId);

        @Query(nativeQuery = true, value = "SELECT pub.pubid, pub.pmid, pub.doi " +
                        "FROM Publications pub " +
                        "JOIN Publish p ON pub.pubid = p.pubid " +
                        "WHERE p.pid = :personId")
        List<Map<String, Object>> getPersonPublications(@Param("personId") Long personId);

        @Query(nativeQuery = true, value = "SELECT g.grantid, g.budget_start " +
                        "FROM Grants g " +
                        "JOIN Has o ON g.grantid = o.grantid " +
                        "WHERE o.pid = :personId")
        List<Map<String, Object>> getPersonGrants(@Param("personId") Long personId);

        @Query(value = "SELECT p.pid as pid, p.name as name, p.major as major, COUNT(pub.pubid) as pubCount " +
                        "FROM People p " +
                        "JOIN \"in\" i ON p.pid = i.pid " +
                        "JOIN Locations l ON i.locid = l.locid " +
                        "LEFT JOIN publish pub ON p.pid = pub.pid " +
                        "WHERE l.loc_name = :locName AND p.major LIKE %:major% " +
                        "GROUP BY p.pid, p.name, p.major " +
                        "ORDER BY pubCount DESC", nativeQuery = true)
        List<Object[]> getPeopleByPublicationCount(@Param("locName") String locName, @Param("major") String major);

        @Query(value = "SELECT p.pid as pid, p.name as name, p.major as major, p.hindex as hindex " +
                        "FROM People p " +
                        "JOIN \"in\" i ON p.pid = i.pid " +
                        "JOIN Locations l ON i.locid = l.locid " +
                        "WHERE l.loc_name = :locName AND p.major LIKE %:major% " +
                        "ORDER BY p.hindex DESC", nativeQuery = true)
        List<Object[]> getPeopleByHIndex(@Param("locName") String locName, @Param("major") String major);

        @Query(value = "SELECT DISTINCT p.pid as pid, p.name as name, p.major as major, p.hindex as hindex " +
                        "FROM People p " +
                        "JOIN \"in\" i ON p.pid = i.pid " +
                        "JOIN Locations l ON i.locid = l.locid " +
                        "JOIN Publish pub ON p.pid = pub.pid " +
                        "WHERE l.loc_name = :locName AND p.major LIKE %:major% " +
                        "AND p.pid NOT IN (SELECT pid FROM Has)", nativeQuery = true)
        List<Object[]> getPeopleWithPublicationsNoGrants(@Param("locName") String locName,
                        @Param("major") String major);
}
