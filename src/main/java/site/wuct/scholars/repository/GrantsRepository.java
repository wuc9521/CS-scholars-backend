package site.wuct.scholars.repository;

import site.wuct.scholars.model.Grant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GrantsRepository extends JpaRepository<Grant, Long> {

    // @Query("SELECT g.grantid as grantId, g.budget_start as budgetStart " +
    //        "FROM Grant g " +
    //        "JOIN Obtain o ON g.grantid = o.grantid " +
    //        "WHERE o.pid = :personId")
    // List<Object[]> getGrantsReceivedByPerson(@Param("personId") Long personId);

}