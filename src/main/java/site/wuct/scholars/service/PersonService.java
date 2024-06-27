package site.wuct.scholars.service;

import site.wuct.scholars.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();
    Person findById(Long id);
    Person save(Person Person);
    void deleteById(Long id);
}

