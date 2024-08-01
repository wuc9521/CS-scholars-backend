package site.wuct.scholars.repository;

import site.wuct.scholars.model.Location;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationsRepository extends JpaRepository<Location, Integer> {
  @Query(value = "SELECT l.locid as locid, l.loc_name as locName, COUNT(p.pid) as peopleCount " +
           "FROM Locations l " +
           "JOIN \"in\" i ON l.locid = i.locid " +
           "JOIN People p ON i.pid = p.pid " +
           "WHERE p.major LIKE %:major% " +
           "GROUP BY l.locid, l.loc_name " +
           "ORDER BY peopleCount DESC", nativeQuery = true)
    List<Object[]> getLocationsByMajorCount(@Param("major") String major);

    @Query(value = "SELECT l.locid as locid, l.loc_name as locName, COUNT(DISTINCT g.grantid) as grantCount " +
           "FROM Locations l " +
           "JOIN \"in\" i ON l.locid = i.locid " +
           "JOIN People p ON i.pid = p.pid " +
           "LEFT JOIN Has o ON p.pid = o.pid " +
           "LEFT JOIN Grants g ON o.grantid = g.grantid " +
           "WHERE p.major LIKE %:major% " +
           "GROUP BY l.locid, l.loc_name " +
           "ORDER BY grantCount DESC", nativeQuery = true)
    List<Object[]> getLocationsByGrantCount(@Param("major") String major);

    @Query(value = "SELECT l.locid as locid, l.loc_name as locName, MAX(p.hindex) as maxHIndex " +
           "FROM Locations l " +
           "JOIN \"in\" i ON l.locid = i.locid " +
           "JOIN People p ON i.pid = p.pid " +
           "WHERE p.major LIKE %:major% " +
           "GROUP BY l.locid, l.loc_name " +
           "ORDER BY maxHIndex DESC", nativeQuery = true)
    List<Object[]> getLocationsByMaxHIndex(@Param("major") String major);

    @Query(value = "SELECT l.*, " +
           "(SELECT COUNT(DISTINCT p.pid) FROM People p JOIN \"in\" i ON p.pid = i.pid WHERE i.locid = l.locid) as scholar_count, " +
           "(SELECT COUNT(DISTINCT pub.pubid) FROM Publications pub JOIN Publish pu ON pub.pubid = pu.pubid JOIN People p ON pu.pid = p.pid JOIN \"in\" i ON p.pid = i.pid WHERE i.locid = l.locid) as publication_count, " +
           "(SELECT COUNT(DISTINCT g.grantid) FROM Grants g JOIN Has h ON g.grantid = h.grantid JOIN People p ON h.pid = p.pid JOIN \"in\" i ON p.pid = i.pid WHERE i.locid = l.locid) as grant_count " +
           "FROM Locations l WHERE l.locid = :locid", nativeQuery = true)
    Map<String, Object> getLocationProfile(@Param("locid") Integer locid);

    @Query(value = "SELECT p.pid, p.name, p.major, p.hindex, COUNT(DISTINCT pub.pubid) as publication_count " +
           "FROM People p " +
           "JOIN \"in\" i ON p.pid = i.pid " +
           "LEFT JOIN Publish pu ON p.pid = pu.pid " +
           "LEFT JOIN Publications pub ON pu.pubid = pub.pubid " +
           "WHERE i.locid = :locid " +
           "GROUP BY p.pid, p.name, p.major, p.hindex " +
           "ORDER BY publication_count DESC", nativeQuery = true)
    List<Map<String, Object>> getLocationScholarsSortedByPublications(@Param("locid") Integer locid);
}
