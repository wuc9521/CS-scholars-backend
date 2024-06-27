package site.wuct.scholars.repository;

import site.wuct.scholars.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
