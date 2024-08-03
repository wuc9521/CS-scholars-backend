package site.wuct.scholars.repository;

import site.wuct.scholars.model.Grant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HasRepository extends JpaRepository<Grant, Long> {
    /**
     * Get all publications by person id
     * 
     * @param pid person id
     * @return publications list
     */
    @Query(value = "SELECT \"grants\".* FROM grants JOIN \"has\" ON \"grants\".grantid = \"has\".grantid WHERE \"has\".pid = :pid", nativeQuery = true)
    public List<Grant> findGrantsByPersonId(Integer pid);
}
