package site.wuct.scholars.repository;

import site.wuct.scholars.model.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Long> {
}
