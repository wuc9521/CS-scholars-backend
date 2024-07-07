package site.wuct.scholars.repository;

import site.wuct.scholars.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationsRepository extends JpaRepository<Location, Long> {
    
}
