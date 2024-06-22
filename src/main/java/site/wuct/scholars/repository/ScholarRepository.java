package site.wuct.scholars.repository;

import site.wuct.scholars.model.Scholar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScholarRepository extends JpaRepository<Scholar, Long> {
}
