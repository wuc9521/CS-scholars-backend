package site.wuct.scholars.repository;

import site.wuct.scholars.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationsRepository extends JpaRepository<Publication, Long> {
    
}
