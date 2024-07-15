package site.wuct.scholars.repository;

import site.wuct.scholars.model.Person;
import site.wuct.scholars.model.Location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InRepository extends JpaRepository<Person, Long> {

    /**
     * Get all People by location id
     * 
     * @param locid location id
     * @return People list
     */
    @Query(value = "SELECT P.* FROM people AS P JOIN \"in\" AS I ON P.pid = I.pid WHERE I.locid = :locid", nativeQuery = true)
    List<Person> findPeopleByLocationId(@Param("locid") Integer locid);

    /**
     * Get all Locations by person id
     * @param pid person id
     * @return Locations list
     */
    @Query(value = "SELECT L.* FROM locations AS L JOIN \"in\" AS I ON L.locid = I.locid WHERE I.pid = :pid", nativeQuery = true)
    List<Location> findLocationsByPersonId(Integer pid);
}