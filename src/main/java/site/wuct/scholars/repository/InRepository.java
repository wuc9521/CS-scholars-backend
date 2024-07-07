package site.wuct.scholars.repository;


import site.wuct.scholars.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
public interface InRepository extends JpaRepository<Person, Long> {

    
} 