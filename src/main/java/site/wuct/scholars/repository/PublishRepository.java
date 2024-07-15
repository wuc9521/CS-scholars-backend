package site.wuct.scholars.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import site.wuct.scholars.model.Publication;

public interface PublishRepository extends JpaRepository<Publication, Long> {

    /**
     * Get all publications by person id
     * 
     * @param pid person id
     * @return publications list
     */
    @Query(value = "SELECT publications.* FROM publications JOIN \"publish\" ON publications.pubid = publish.pubid WHERE publish.pid = :pid", nativeQuery = true)
    public List<Publication> findPublicationsByPersonId(Integer pid);

}
