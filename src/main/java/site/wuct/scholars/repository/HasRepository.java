package site.wuct.scholars.repository;

import site.wuct.scholars.model.Grant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HasRepository extends JpaRepository<Grant, Long> {
    
}
